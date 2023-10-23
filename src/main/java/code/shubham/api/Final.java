package code.shubham.api;

import com.google.gson.Gson;
import javax.net.ssl.HttpsURLConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class Final {

    public static void main(String[] args) {
        System.out.println(invoke(2, "Pulmonary embolism"));
    }

    static int invoke(int doctorId, String diagnosisName) {
        List<Integer> results = invokeAPI("https://jsonmock.hackerrank.com/api/medical_records?page=%s", doctorId, diagnosisName);
        return results.stream().mapToInt(e -> e).sum() / results.size();
    }

    static Gson gson = new Gson();

    static class Response {
        int page;
        int per_page;
        int total;
        int total_pages;
        List<Data> data;
    }

    static class Data {
        int id;
        Diagnosis diagnosis;
        Doctor doctor;
        Vitals vitals;
    }

    class Doctor {
        int id;
    }

    class Vitals {
        int pulse;
    }

    class Diagnosis {
        String name;
    }

    static List<Integer> invokeAPI(String uri, int doctorId, String diagnosisName) {
        List<Integer> result = new ArrayList<>();
        try {
            final Response resp = invoke(String.format(uri, 1));
            ExecutorService executor = Executors.newFixedThreadPool(10);
            List<Future<Response>> f = executor.invokeAll(
                IntStream.rangeClosed(1, resp.total_pages)
                        .mapToObj(
                                i -> (Callable<Response>) (() -> invoke(String.format(uri, i)))
                        )
                        .toList()
            );

            for (Future<Response> r : f)
                for (Data data : r.get().data)
                    if (data != null && data.doctor.id == doctorId && data.diagnosis.name.equals(diagnosisName))
                        result.add(data.vitals.pulse);
            while (!executor.isTerminated())
                executor.shutdown();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    static Response invoke(String uri) throws IOException {
        URL url = new URL(uri);
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        con.setRequestProperty("accept", "application/json");
        con.getResponseCode();

        String inp;
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder c = new StringBuilder();
        while ((inp = br.readLine()) != null)
            c.append(inp);
        br.close();
//        System.out.println(c.toString());
        return gson.fromJson(c.toString(), Response.class);
    }
}
