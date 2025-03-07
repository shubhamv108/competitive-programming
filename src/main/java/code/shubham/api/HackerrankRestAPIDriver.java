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
        System.out.println(Result4.invoke("Seattle"));
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
        String winner = new HackerRankAPIInvoker().get("/football_competitions?name=" + competition + "&year=" + year + "&page=%s", ResponseCompetition.class)
                .map(DataCompetition::getWinner)
                .findFirst()
                .orElse("");
        int team1Goals = new HackerRankAPIInvoker().get("/football_matches?competition=" + competition + "&year=" + year + "&team1=" + winner + "&page=%s", ResponseMatches.class)
                .mapToInt(DataMatches::getTeam1goals)
                .sum();
        int team2Goals = new HackerRankAPIInvoker().get("/football_matches?competition=" + competition + "&year=" + year + "&team2=" + winner + "&page=%s", ResponseMatches.class)
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
        return new HackerRankAPIInvoker().get("/countries?name=" + country, ResponseCityData.class)
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
        return new HackerRankAPIInvoker().get("/medical_records?page=%s", ResponseData.class)
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

class Result4 {

    class UserRating {
        double average_rating;
    }

    class Data {
        String name;
        int estimated_cost;
        UserRating user_rating;
    }

    class ResponseData extends HackerRankAPIResponse<Data> {}

    public static String invoke(String city) {
        return new HackerRankAPIInvoker().get("/food_outlets?city=" + city + "&page=%s", ResponseData.class)
                .sorted((x, y) -> x.user_rating.average_rating == y.user_rating.average_rating ? x.estimated_cost - y.estimated_cost : (int) (y.user_rating.average_rating - x.user_rating.average_rating))
                .findFirst()
                .get()
                .name;
    }
}

// Copy-Paste this class
class HackerRankAPIResponse<D> {
    int total_pages;
    List<D> data;
}

class HackerRankAPIInvoker {

    static Gson GSON = new Gson();

    String BASE_URL = "https://jsonmock.hackerrank.com/api";

    public <D> Stream<D> get(
            String url,
            Class<? extends HackerRankAPIResponse<D>> clazz) {
        String finalUrl = BASE_URL + url;
        HackerRankAPIResponse<D> firstPageResponse = invoke(String.format(finalUrl, 1), clazz);
        return Stream.concat(Stream.of(firstPageResponse), IntStream.rangeClosed(2, firstPageResponse.total_pages)
                        .parallel()
                        .mapToObj(pageNumber -> String.format(finalUrl, pageNumber))
                        .map(uri -> invoke(uri, clazz)))
                .map(r -> r.data)
                .flatMap(List::stream);
    }

    public static <R> R invoke(String url, Class<R> clazz) {
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpResponse<String> response = client.send(
                    HttpRequest.newBuilder(new URI(url.replace(" ", "%20"))).GET().build(),
                    BodyHandlers.ofString());
            String body = response.body();
            return GSON.fromJson(body, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

