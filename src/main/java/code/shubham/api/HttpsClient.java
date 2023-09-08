package code.shubham.api;

import com.google.gson.Gson;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpsClient<Response> {
    private static final Gson GSON = new Gson();

    public Response invoke(final String url, final Class<Response> responseClass) throws IOException {
//        System.out.println("Invoking Url: " + url);
        String finalUrl = url.replaceAll("\\s", "%20");
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
//        System.out.println(response);
        return response;
    }

    private Response parseJsonString(final String jsonString, final Class<Response> responseClass) {
        return GSON.fromJson(jsonString, responseClass);
    }
}