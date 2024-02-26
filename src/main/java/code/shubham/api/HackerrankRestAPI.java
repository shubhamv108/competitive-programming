package code.shubham.api;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.stream.*;

import com.google.gson.*;
import javax.net.ssl.*;

public class HackerrankRestAPI {
    class Solution {
        private static Gson GSON = new Gson();

        public int invoke(int doctorId, String diagnosisName) {
            return invokeAPI("https://jsonmock.hackerrank.com/api/medical_records?page=%s")
                    .filter(data -> data.doctor.id == doctorId && data.diagnosis.name.equals(diagnosisName))
                    .mapToInt(data -> data.vitals.pulse)
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

        public Stream<Data> invokeAPI(String url) {
            Response resp = invoke(String.format(url, 1));
            return Stream.concat(Stream.of(resp), IntStream.rangeClosed(2, resp.total_pages)
                    .parallel()
                    .mapToObj(i -> String.format(url, i))
                    .map(this::invoke))
                    .map(r -> r.data)
                    .flatMap(List::stream);
        }

        public Response invoke(String url) {
            final StringBuilder c = new StringBuilder();
            try {
                final HttpsURLConnection con = (HttpsURLConnection) new URL(url).openConnection();
                con.getResponseCode();
                try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                    String l;
                    while ((l = br.readLine()) != null)
                        c.append(l);
                }
                con.disconnect();
            } catch (final Exception ex) {
                ex.printStackTrace();
            }
            return GSON.fromJson(c.toString(), Response.class);
        }
    }

    public static void main(String[] args) {
        System.out.println(new HackerrankRestAPI().new Solution().invoke(2, "Pulmonary embolism"));
    }
}
