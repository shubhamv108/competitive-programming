package code.shubham.api;

import com.google.gson.Gson;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DrawnMatches {

    /*
     * Complete the 'getNumDraws' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER year as parameter.
     */

    public static int getNumDraws(int year) {
        String endpoint = String.format("https://jsonmock.hackerrank.com/api/football_matches?year=%s", year);
        return (int) invoke(endpoint, year, data -> data.getTeam1goals() == data.getTeam2goals());
    }

    private static long invoke(String endpoint, int year, Function<Data, Boolean> resultDataExtractor) {
        HackerEarthAPIInvoker<Boolean> apiInvoker = new HackerEarthAPIInvoker<>(endpoint);
        List<Boolean> result = apiInvoker.invoke(resultDataExtractor, year);
        return result.stream().filter(e -> e).count();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int year = Integer.parseInt(bufferedReader.readLine().trim());

        int result = DrawnMatches.getNumDraws(year);

        System.out.println(result);


        bufferedReader.close();
    }

    private static class HackerEarthAPIInvoker<Result> {

        private final String formattableUrl;
        private final HttpsClient<Response> httpsClient;

        public HackerEarthAPIInvoker(final String formattableUrl) {
            this.formattableUrl = formattableUrl + "&page=%s";
            this.httpsClient = new HttpsClient<>();
        }

        public List<Result> invoke(final Function<Data, Result> dataResultExtractor, final Object... formatFieldValues) {
            List<Result> results = new ArrayList<>();
            try {
                int totalPages = -1, curPage = 1;
                while (totalPages == -1 || curPage <= totalPages) {
                    String url = String.format(this.formattableUrl, curPage);
                    System.out.println(url);
                    Response response = this.httpsClient.invoke(url, Response.class);
                    if (response == null) return results;

                    if (totalPages == -1) totalPages = response.getTotal_pages();

                    if (totalPages > 10) return invokeConcurrently(dataResultExtractor, totalPages, formatFieldValues);

                    for (final Data data : response.getData()) {
                        Result result = dataResultExtractor.apply(data);
                        if (result != null) results.add(result);
                    }
                    curPage++;
                }
            } catch (final Exception ex) {
                ex.printStackTrace();
            }
            return results;
        }

        public List<Result> invokeConcurrently(final Function<Data, Result> dataResultExtractor, final int pages, final Object... formatFieldValues) {
            List<Future<List<Result>>> results = null;
            try {
                List<Callable<List<Result>>> tasks = getTasks(dataResultExtractor, pages);
                ExecutorService executorService = Executors.newFixedThreadPool(10);
                results = executorService.invokeAll(tasks);

            } catch (InterruptedException ex) {
                System.out.println(ex);
            } catch (Exception ex) {
                System.out.println(ex);
            }

            return results.stream().map(f -> {
                try {
                    return f.get();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                return new ArrayList<Result>();
            }).flatMap(l -> l.stream()).collect(Collectors.toList());
        }

        private List<Callable<List<Result>>> getTasks(final Function<Data, Result> dataResultExtractor, int pages) {
            return IntStream.rangeClosed(1, pages).mapToObj(page -> this.getTask(dataResultExtractor, page)).collect(Collectors.toList());
        }

        private Callable<List<Result>> getTask(final Function<Data, Result> dataResultExtractor, int page) {
            return () -> {
                List<Result> results = new ArrayList<>();
                String url = String.format(this.formattableUrl, page);
                System.out.println(url);
                Response response = this.httpsClient.invoke(url, Response.class);
                if (response == null)
                    return results;

                for (final Data data : response.getData()) {
                    Result result = dataResultExtractor.apply(data);
                    if (result != null)
                        results.add(result);
                }
                return results;
            };
        }
    }

    private static class HttpsClient<Response> {
        private static final Gson GSON = new Gson();

        public Response invoke(final String url, final Class<Response> responseClass) throws IOException {
            String finalUrl = url.replaceAll("\\s", "%20");
            // System.out.println("Invoking Url: " + finalUrl);
            return invoke(new URL(finalUrl), responseClass);
        }

        private Response invoke(final URL url, final Class<Response> responseClass) throws IOException {
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");
            int status = connection.getResponseCode();
            Object response = null;
            if (status == 200) {
                response = this.parseResponse(connection.getInputStream(), responseClass);
            }
            return (Response) response;
        }

        private Response parseResponse(final InputStream inputStream, final Class<Response> responseClass) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = br.readLine()) != null) {
                content.append(inputLine);
            }
            br.close();
            Response response = this.parseJsonString(content.toString(), responseClass);
//            System.out.println(response);
            return response;
        }

        private Response parseJsonString(final String jsonString, final Class<Response> responseClass) {
            return GSON.fromJson(jsonString, responseClass);
        }
    }

    private class Data {
        private String team1;
        private String team2;
        private String team1goals;
        private String team2goals;

        public int getTeam1goals() {
            return Integer.valueOf(team1goals);
        }

        public int getTeam2goals() {
            return Integer.valueOf(team2goals);
        }

        @Override
        public String toString() {
            return "Data{" + "team1=" + team1 + ", team2=" + team2 + ", team1goals=" + team1goals + ", team2goals=" + team2goals + '}';
        }
    }

    private class Response {
        private int page;
        private int per_page;
        private int total;
        private int total_pages;
        private List<Data> data;

        public int getTotal_pages() {
            return total_pages;
        }

        public List<Data> getData() {
            return data;
        }

        @Override
        public String toString() {
            return "Response{" + "page=" + page + ", per_page=" + per_page + ", total=" + total + ", total_pages=" + total_pages + ", data=" + data + '}';
        }
    }

}
