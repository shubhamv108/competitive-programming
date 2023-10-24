package code.shubham.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.google.gson.Gson;
import javax.net.ssl.HttpsURLConnection;

public class HackerrankRestAPI {

    static Gson gson = new Gson();
    public static int invoke(int doctorId, String diagnosisName) {
        return invokeAPI("https://jsonmock.hackerrank.com/api/medical_records?page=%s")
                .map(r -> r.data)
                .flatMap(List::stream)
                .filter(data -> data.doctor.id == doctorId && data.diagnosis.name.equals(diagnosisName))
                .map(data -> data.vitals.pulse)
                .mapToInt(e -> e)
                .average()
                .stream()
                .mapToLong(Math::round)
                .mapToInt(e -> (int) e)
                .findAny()
                .orElse(0);
    }

    class Response {
        int page;
        int per_page;
        int total;
        int total_pages;
        List<Data> data;
    }

    class Data {
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

    static Stream<Response> invokeAPI(String url) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        try {
            Response resp = invoke(String.format(url, 1));
            List<Callable<Response>> l = IntStream.rangeClosed(2, resp.total_pages)
                    .mapToObj(
                            i -> (Callable<Response>) (() -> invoke(String.format(url, i)))
                    ).toList();
            return Stream.concat(Stream.of(resp), executor
                    .invokeAll(l)
                    .stream()
                    .map(f -> {
                        try {
                            return f.get();
                        } catch (Exception ex) {
                        }
                        return null;
                    }));
        } catch (Exception ex) {
        } finally {
            while (!executor.isTerminated())
                executor.shutdown();
        }
        return Stream.of();
    }

    static Response invoke(String url) throws IOException {
        HttpsURLConnection con = (HttpsURLConnection) new URL(url).openConnection();
        con.getResponseCode();

        StringBuilder c = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inp;
            while ((inp = br.readLine()) != null)
                c.append(inp);
        }
        return gson.fromJson(c.toString(), Response.class);
    }

    public static void main(String[] args) {
        System.out.println(invoke(2, "Pulmonary embolism"));
    }
}
