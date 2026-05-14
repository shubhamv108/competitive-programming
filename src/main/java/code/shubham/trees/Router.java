package code.shubham.trees;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Router {

    private final List<Route> routes = new ArrayList<>();
    private final Node tree = new Node();

    public Router() {
        routes.add(new Route(HttpMethod.GET, "/networks", new Endpoint()));
        routes.add(new Route(HttpMethod.POST, "/networks", new Endpoint()));
        routes.add(new Route(HttpMethod.POST, "/networks/*/devices", new Endpoint()));
        routes.add(new Route(HttpMethod.GET, "/networks/*/devices", new Endpoint()));
        routes.add(new Route(HttpMethod.DELETE, "/networks/*/devices", new Endpoint()));
        routes.add(new Route(HttpMethod.GET, "/chunks/*", new Endpoint()));
        routes.add(new Route(HttpMethod.POST, "/chunks/", new Endpoint()));

        for (Route route : routes) {
            String[] path = route.getPath().split("/");
            int startIndex = 0;
            if (path[0] == "")
                startIndex = 1;
            tree.add(route.getMethod(), path, startIndex, route.getEndpoint());
        }
    }

    public Optional<Endpoint> route(HttpMethod method, URI uri) {
        String[] path = uri.getPath().split("/");
        int startIndex = 0;
        if (path[0] == "")
            startIndex = 1;
        Optional<Endpoint> result = Optional.ofNullable(tree.get(method, path, startIndex));
        if (result.isPresent())
            result.get().invoke(uri.getPath());
        return result;
    }

    class Endpoint {
        public void invoke(String uri) {
            System.out.println("invokedURI=" + uri);
        }
    }

    enum HttpMethod {
        GET, POST, PUT, PATCH, DELETE, OPTION,
    }

    class Route {
        private final HttpMethod method;
        private final String path;
        private final Endpoint endpoint;

        Route(HttpMethod method, String path, Endpoint endpoint) {
            this.method = method;
            this.path = path;
            this.endpoint = endpoint;
        }

        public Endpoint getEndpoint() {
            return endpoint;
        }

        public HttpMethod getMethod() {
            return method;
        }

        public String getPath() {
            return path;
        }
    }

    class Node {
        Map<String, Node> next = new HashMap<>();
        Map<HttpMethod, Endpoint> endpoints = new HashMap<>();

        void add(HttpMethod method, String[] path, int idx, Endpoint endpoint) {
            if (idx == path.length) {
                if (endpoints.get(method) != null) {
                    throw new RuntimeException("Path already has an endpoint conf");
                }
                endpoints.put(method, endpoint);
                return;
            }

            next.computeIfAbsent(path[idx], e -> new Node())
                    .add(method, path, idx + 1, endpoint);
        }

        Endpoint get(HttpMethod method, String[] path, int idx) {
            if (idx == path.length)
                return endpoints.get(method);

            Node n = Optional.ofNullable(next.get(path[idx]))
                    .orElse(next.get("*"));

            if (n == null)
                return null;

            return n.get(method, path, idx + 1);
        }
    }

    public static void main(String[] args) throws URISyntaxException {
        Router router = new Router();
        router.route(HttpMethod.GET, new URI("/networks"));
        router.route(HttpMethod.POST, new URI("/networks"));
        router.route(HttpMethod.POST, new URI("/networks/1/devices"));
        router.route(HttpMethod.PATCH, new URI("/networks/1/devices"));
        router.route(HttpMethod.GET, new URI("/networks/1/devices"));
        router.route(HttpMethod.GET, new URI("/networks/1/"));
        router.route(HttpMethod.GET, new URI("/chunks/1/"));
    }

}
