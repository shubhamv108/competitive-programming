package code.contestpractice.templates;

import com.google.gson.Gson;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

class Solution {
    public static List<String> get(String city, int maxCost) {
        String url = "https://jsonmock.hackerrank.com/api/operation?key=%s";
        Function<Data, String> resultExtractor = (data) ->
                data.estimated_cost < maxCost
                        ? data.name
                        : null;
        return new HackerEarthAPIInvoker<String>(url, resultExtractor)
                .invokeAndGetResult(city);
    }
}

class Response {
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
        return "Response{" +
                "page=" + page +
                ", per_page=" + per_page +
                ", total=" + total +
                ", total_pages=" + total_pages +
                ", data=" + data +
                '}';
    }
}

class Data {
    String city;
    String name;
    int estimated_cost;
    UserRating user_rating;
    int id;

    @Override
    public String toString() {
        return "Data{" +
                "city='" + city + '\'' +
                ", name='" + name + '\'' +
                ", user_rating=" + user_rating +
                ", id=" + id +
                '}';
    }
}

class UserRating {
    double average_rating;
    int votes;

    @Override
    public String toString() {
        return "UserRating{" +
                "average_rating=" + average_rating +
                ", votes=" + votes +
                '}';
    }
}

class HackerEarthAPIInvoker<Result> {
    private final String formattableUrl;
    private final HttpsClient<Response> httpsClient;
    private final Function<Data, Result> dataResultExtractor;
    public HackerEarthAPIInvoker(final String formattableUrl, final Function<Data, Result> dataResultExtractor) {
        this.formattableUrl = formattableUrl.replaceAll("\\s", "+");
        this.httpsClient = new HttpsClient<>();
        this.dataResultExtractor = dataResultExtractor;
    }

    public List<Result> invokeAndGetResult(final Object... formatFieldValues) {
        List<Result> results = new ArrayList<>();
        try {
            int totalPages = -1, curPage = 1;
            String[] formatFieldStringValues = Arrays.stream(formatFieldValues)
                    .map(String::valueOf)
                    .map(field -> field.replaceAll("\\s", "+"))
                    .toArray(String[]::new);
            while (totalPages == -1 || curPage <= totalPages) {
                String url = String.format(this.formattableUrl, formatFieldStringValues, curPage);
                Response response = this.httpsClient.invoke(url, Response.class);
                if (totalPages == -1) {
                    totalPages = response.getTotal_pages();
                }
                for (final Data data : response.getData()) {
                    Result result = dataResultExtractor.apply(data);
                    if (result != null) {
                        results.add(result);
                    }
                }
                curPage++;
            }
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
        return results;
    }
}

class HttpsClient<Response> {
    private final Gson GSON = new Gson();

    public Response invoke(final String url, final Class<Response> responseClass) throws IOException {
//        System.out.println("Invoking Url: " + url);
        return invoke(new URL(url), responseClass);
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

    private Response parseResponse(final InputStream inputStream, final Class<Response> responseClass)
            throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = br.readLine()) != null) {
            content.append(inputLine);
        }
        br.close();
        Response response = this.parseJsonString(content.toString(), responseClass);
//        System.out.println(response);
        return response;
    }

    private Response parseJsonString(final String jsonString, final Class<Response> responseClass) {
        return GSON.fromJson(jsonString, responseClass);
    }
}
