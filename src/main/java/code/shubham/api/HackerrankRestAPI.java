package code.shubham.api;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.stream.*;

import com.google.gson.*;
import javax.net.ssl.*;

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
        int total_pages;
        List<Data> data;
    }

    class Data {
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
        Response resp = invoke(String.format(url, 1));
        return Stream.concat(Stream.of(resp), IntStream.rangeClosed(2, resp.total_pages)
                .parallel()
                .mapToObj(i -> String.format(url, i))
                .map(HackerrankRestAPI::invoke));
    }

    static Response invoke(String url) {
        try {
            HttpsURLConnection con = (HttpsURLConnection) new URL(url).openConnection();
            con.getResponseCode();

            StringBuilder c = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inp;
                while ((inp = br.readLine()) != null)
                    c.append(inp);
            }
            return gson.fromJson(c.toString(), Response.class);
        } catch (Exception ex) {}
        return null;
    }

    public static void main(String[] args) {
        System.out.println(invoke(2, "Pulmonary embolism"));
    }
}
