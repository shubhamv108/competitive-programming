package code.shubham.api;

import java.io.*;
import java.util.*;
import java.util.stream.*;

// Copy-Paste these 3 imports
import com.google.gson.*;
import java.net.*;
import javax.net.ssl.*;

public class HackerrankRestAPIDriver {
    public static void main(String[] args) {
//        System.out.println(Result.getWinnerTotalGoals("UEFA Champions League", 2011));
//        System.out.println(Result2.getCapitalCity("Afghanistan"));
        System.out.println(Result3.invoke(2, "Pulmonary embolism"));
    }
}

class Result {

    class DataCompetition {
        String winner;

        public String getWinner() {
            return winner;
        }
    }

    class DataMatches {
        private int team1goals;
        private int team2goals;

        public int getTeam1goals() {
            return this.team1goals;
        }

        public int getTeam2goals() {
            return this.team2goals;
        }
    }

    class ResponseCompetition extends HackerRankAPIInvoker.Response<DataCompetition> {
    }

    class ResponseMatches extends HackerRankAPIInvoker.Response<DataMatches> {
    }

    public static int getWinnerTotalGoals(String competition, int year) {
        final String winner = HackerRankAPIInvoker.invokeAPI("/football_competitions?name=" + competition + "&year=" + year + "&page=%s", ResponseCompetition.class)
                .map(DataCompetition::getWinner)
                .findFirst()
                .orElse("");
        final int team1Goals = HackerRankAPIInvoker.invokeAPI("/football_matches?competition=" + competition + "&year=" + year + "&team1=" + winner + "&page=%s", ResponseMatches.class)
                .mapToInt(DataMatches::getTeam1goals)
                .sum();
        final int team2Goals = HackerRankAPIInvoker.invokeAPI("/football_matches?competition=" + competition + "&year=" + year + "&team2=" + winner + "&page=%s", ResponseMatches.class)
                .mapToInt(DataMatches::getTeam2goals)
                .sum();
        return team1Goals + team2Goals;
    }
}

class Result2 {
    class CityData {
        private String capital;

        public String getCapital() {
            return capital;
        }
    }

    class ResponseCityData extends HackerRankAPIInvoker.Response<CityData> {}

    public static String getCapitalCity(String country) {
        return HackerRankAPIInvoker.invokeAPI("/countries?name=" + country, ResponseCityData.class)
                .findAny()
                .map(CityData::getCapital)
                .orElse("-1");
    }
}

class Result3 {

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

    class ResponseData extends HackerRankAPIInvoker.Response<Data> {}

    public static int invoke(int doctorId, String diagnosisName) {
        return HackerRankAPIInvoker.invokeAPI("/medical_records?page=%s", ResponseData.class)
                .filter(data -> data.doctor.id == doctorId && data.diagnosis.name.equals(diagnosisName))
                .mapToInt(data -> data.vitals.pulse)
                .average()
                .stream()
                .mapToLong(Math::round)
                .mapToInt(e -> (int) e)
                .findAny()
                .orElse(0);
    }
}

// Copy-Paste this class
class HackerRankAPIInvoker {
    private static final String BASE_URL = "https://jsonmock.hackerrank.com/api";

    public static abstract class Response<Data> {
        private int total_pages;

        private List<Data> data;

        public int getTotalPages() {
            return total_pages;
        }

        public List<Data> getData() {
            return data;
        }
    }

    public static <Data> Stream<Data> invokeAPI(
            final String url,
            final Class<? extends Response<Data>> clazz) {
        final Response firstPageResponse = HttpAPIInvoker.invoke(String.format(BASE_URL + url, 1), clazz);
        return Stream.concat(Stream.of(firstPageResponse), IntStream.rangeClosed(2, firstPageResponse.getTotalPages())
                        .parallel()
                        .mapToObj(i -> String.format(BASE_URL + url, i))
                        .map(uri -> HttpAPIInvoker.invoke(uri, clazz)))
                .map(Response::getData)
                .flatMap(List::stream);
    }
}

// Copy-Paste this class
class HttpAPIInvoker {
    private static final Gson GSON = new Gson();

    public static <Response> Response invoke(final String url, final Class<Response> clazz) {
        final String finalUrl = url.replace(" ", "%20");
        final StringBuilder responseBuilder = new StringBuilder();
        try {
            final HttpsURLConnection connection = (HttpsURLConnection) new URL(finalUrl).openConnection();
            connection.getResponseCode();
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String l;
                while ((l = bufferedReader.readLine()) != null)
                    responseBuilder.append(l);
            }
            connection.disconnect();
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
        return GSON.fromJson(responseBuilder.toString(), clazz);
    }
}
