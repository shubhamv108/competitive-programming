package code.shubham.api;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    class ResponseCompetition extends HackerRankAPIResponse<DataCompetition> {
    }

    class ResponseMatches extends HackerRankAPIResponse<DataMatches> {
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

    class ResponseCityData extends HackerRankAPIResponse<CityData> {}

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

    class ResponseData extends HackerRankAPIResponse<Data> {}

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
abstract class HackerRankAPIResponse<Data> {
    private int total_pages;

    private List<Data> data;

    public int getTotalPages() {
        return total_pages;
    }

    public List<Data> getData() {
        return data;
    }
}

// Copy-Paste this class
class HackerRankAPIInvoker {

    private static final Gson GSON = new Gson();

    private static final String BASE_URL = "https://jsonmock.hackerrank.com/api";

    public static <Data> Stream<Data> invokeAPI(
            final String url,
            final Class<? extends HackerRankAPIResponse<Data>> clazz) {
        final String finalUrl = BASE_URL + url;
        final HackerRankAPIResponse<Data> firstPageResponse = HttpClientDirector.invoke(String.format(finalUrl, 1), clazz);
        return Stream.concat(Stream.of(firstPageResponse), IntStream.rangeClosed(2, firstPageResponse.getTotalPages())
                        .parallel()
                        .mapToObj(pageNumber -> String.format(finalUrl, pageNumber))
                        .map(uri -> uri.replace(" ", "%20"))
                        .map(uri -> {
                            try {
                                return HttpClient.newHttpClient().send(
                                        HttpRequest.newBuilder(new URI(uri)).GET().build(),
                                        BodyHandlers.ofString());
                            } catch (Exception exception) {
                                throw new RuntimeException(exception);
                            }
                        })
                        .map(HttpResponse::body)
                        .map(body -> GSON.fromJson(body, clazz)))
                .map(HackerRankAPIResponse::getData)
                .flatMap(List::stream);
    }
}

