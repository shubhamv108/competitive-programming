package code.shubham.api;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static code.shubham.api.RestAPIService.HttpExchangeUtils.send;

public class RestAPIService {

    public class HttpExchangeUtils {
        private static final Gson GSON = new Gson();

        private static HashMap<String, Object> readBody(HttpExchange httpExchange) throws IOException {
            return GSON.<HashMap<String, Object>>fromJson(new String(httpExchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8), HashMap.class);
        }

        public static Map<String, String> queryParams(HttpExchange httpExchange) {
            Map<String, String> params = new LinkedHashMap<>();
            String raw = httpExchange.getRequestURI().getRawQuery();
            if (raw == null || raw.isEmpty()) return params;

            for (String pair : raw.split("&")) {
                if (pair.isEmpty()) continue;
                int eq = pair.indexOf('=');
                String key = eq >= 0 ? pair.substring(0, eq) : pair;
                String value = eq >= 0 ? pair.substring(eq + 1) : "";
                params.put(decode(key), decode(value));
            }
            return params;
        }

        public static String[] getPathVariables(HttpExchange httpExchange) {
            String path = httpExchange.getRequestURI().getPath();
            return path.replaceAll("^/+|/+$", "").split("/");
        }

        public static Map<String, String> pathVariables(String template, String path) {
            String[] t = trimSlashes(template).split("/");
            String[] p = trimSlashes(path).split("/");
            if (t.length != p.length)
                return null;

            int n = t.length;
            Map<String, String> vars = new HashMap<>();
            for (int i = 0; i < n; ++i) {
                if (t[i].startsWith("{") && t[i].endsWith("}"))
                    vars.put(t[i].substring(1, t[i].length() - 1), decode(p[i]));
                else if (!t[i].equals(p[i]))
                    return null;
            }
            return vars;
        }

        private static String trimSlashes(String s) {
            int start = 0, end = s.length();
            while (start < end && s.charAt(start) == '/')
                ++start;
            while (end > start && s.charAt(end - 1) == '/')
                --end;
            return s.substring(start, end);
        }

        private static String decode(String s) {
            return URLDecoder.decode(s, StandardCharsets.UTF_8);
        }

        static void send(HttpExchange httpExchange, int responseCode, Object body) throws IOException {
            String json = GSON.toJson(body);
            byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
            httpExchange.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8");
            httpExchange.sendResponseHeaders(responseCode, bytes.length);
            try (OutputStream os = httpExchange.getResponseBody()) {
                os.write(bytes);
            }
        }
    }

    public class KVStore {
        private class StoreEntry {
            private final Object value;
            private final Instant expiryAt;

            private StoreEntry(Object value, Instant expiryAt) {
                this.value = value;
                this.expiryAt = expiryAt;
            }

            public Object getValue() {
                return value;
            }

            public Instant getExpiryAt() {
                return expiryAt;
            }

            public boolean isExpired() {
                return expiryAt == null || Instant.now().isAfter(expiryAt);
            }
        }
        private final ConcurrentHashMap<String, StoreEntry> store = new ConcurrentHashMap<>();

        public Map<String, Object> set(String key, Object value, Integer ttl) {
            Instant expiryAt = null;
            if (ttl != null)
                expiryAt = Instant.now().plusSeconds(ttl);
            StoreEntry entry = new StoreEntry(value, expiryAt);
            store.put(key, entry);
            return getResponse(key, entry);
        }

        public Map<String, Object> get(String key) {
            StoreEntry entry = store.get(key);
            if (entry == null || entry.isExpired())
                return null;
            return getResponse(key, entry);
        }

        public List<String> listKeys() {
            return store
                    .entrySet()
                    .stream()
                    .filter(entry -> !entry.getValue().isExpired())
                    .map(Map.Entry::getKey)
                    .toList();
        }

        public Map<String, Object> delete(String key) {
            StoreEntry entry = store.remove(key);
            return getResponse(key, entry);
        }

        public boolean has(String key) {
            StoreEntry entry = store.get(key);
            return !(entry == null || entry.isExpired());
        }

        private Map<String, Object> getResponse(String key, StoreEntry entry) {
            if (entry.expiryAt == null)
                return Map.of("key", key, "value", entry.getValue());
            return Map.of("key", key, "value", entry.getValue(), "expiresAt", entry.expiryAt.toString());
        }

        public void evictExpired() {
            store.entrySet().removeIf(e -> e.getValue().isExpired());
        }
    }

    public class KVStoreHandler implements HttpHandler {
        private final KVStore kvStore;

        public static final String BASE_PATH = "/api/v1/kvstore";

        public KVStoreHandler(KVStore kvStore) {
            this.kvStore = kvStore;
        }

        @Override
        public void handle(HttpExchange ex) throws IOException {
            try {
                route(ex);
            } catch (Throwable t) {
                t.printStackTrace();
                try {
                    HttpExchangeUtils.send(ex, 500, Map.of("error", "Internal server error"));
                } catch (IOException | RuntimeException ignored) {
                    // response already started or connection gone
                }
            } finally {
                ex.close();
            }
        }

        private void route(HttpExchange httpExchange) throws IOException {
            String method = httpExchange.getRequestMethod();
            String[] pathVariables = HttpExchangeUtils.getPathVariables(httpExchange);
            String key = pathVariables.length < 4 ? null : pathVariables[3];
            switch (method) {
                case "PUT" -> handlePut(httpExchange, key);
                case "GET" -> handleGet(httpExchange, key);
                case "DELETE" -> handleDelete(httpExchange, key);
            }
        }

        private void handlePut(HttpExchange httpExchange, String key) throws IOException {
            HashMap<String, Object> body = HttpExchangeUtils.readBody(httpExchange);
            int responseCode = 201;
            if (kvStore.has(key)) {
                responseCode = 200;
            }
            Map<String, Object> response = kvStore.set(key, body.get("value"), ((Double) body.get("ttl")).intValue());
            send(httpExchange, responseCode, response);
        }

        private void handleGet(HttpExchange httpExchange, String key) throws IOException {
            if (key == null) {
                List<String> keys = kvStore.listKeys();
                Map<String, Object> response = Map.of("keys", keys);
                send(httpExchange, 200, response);
            }
            Map<String, Object> response = kvStore.get(key);
            if (response == null)
                send(httpExchange, 404, Map.of("error", "key not found"));
            send(httpExchange, 200, response);
        }

        private void handleDelete(HttpExchange httpExchange, String key) throws IOException {
            if (!kvStore.has(key))
                send(httpExchange, 404, Map.of("error", "key not found"));
            Map<String, Object> response = kvStore.delete(key);
            send(httpExchange, 200, response);
        }
    }

    public final class KVStoreRestAPIServer {
        private final HttpServer server;
        private final ExecutorService workers = Executors.newFixedThreadPool(8);
        private final ScheduledExecutorService cleaner = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread t = new Thread(r, "kvstore-cleaner");
            t.setDaemon(true);
            return t;
        });

        public KVStoreRestAPIServer(int port) throws IOException {
            KVStore store = new KVStore();
            server = HttpServer.create(new InetSocketAddress(port), 0);
            server.createContext(KVStoreHandler.BASE_PATH, new KVStoreHandler(store));
            server.setExecutor(workers);
            cleaner.scheduleAtFixedRate(store::evictExpired, 30, 30, TimeUnit.SECONDS);
        }

        public void start() {
            server.start();
        }

        public void stop() {
            server.stop(1);
            workers.shutdown();
            cleaner.shutdownNow();
        }
    }

    /**
     * curl -X PUT localhost:8080/api/v1/kvstore/greeting -d '{"value":{"msg":"hi"},"ttl":60}'
     * curl localhost:8080/api/v1/kvstore/greeting
     * curl localhost:8080/api/v1/kvstore
     * curl -X DELETE localhost:8080/api/v1/kvstore/greeting
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        int port = args.length > 0 ? Integer.parseInt(args[0]) : 8080;
        KVStoreRestAPIServer server = new RestAPIService().new KVStoreRestAPIServer(port);
        Runtime.getRuntime().addShutdownHook(new Thread(server::stop));
        server.start();
        System.out.println("KV store running on http://localhost:" + port + KVStoreHandler.BASE_PATH);
    }
}
