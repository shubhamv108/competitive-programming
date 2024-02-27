package code.shubham.api;

import java.io.*;
import java.util.*;
import java.util.stream.*;


import com.google.gson.*;
import java.net.*;
import javax.net.ssl.*;

public class HackerrankRestAPI {
    class Result {

        class DataCompetition {
            String name;
            int year;
            String winner;
        }

        class DataMatches {
            int team1goals;
            int team2goals;
        }

        class ResponseCompetition extends Response<DataCompetition> {}
        class ResponseMatches extends Response<DataMatches> {}

        public static int getWinnerTotalGoals(String competition, int year) {
             final String winner = Result.invokeAPI("https://jsonmock.hackerrank.com/api/football_competitions?name=" + competition + "&year=" + year + "&page=%s", ResponseCompetition.class)
                     .map(dataCompetition -> dataCompetition.winner)
                     .findFirst()
                     .orElse("");
             final int team1Goals = Result.invokeAPI("https://jsonmock.hackerrank.com/api/football_matches?competition=" + competition + "&year=" + year + "&team1=" + winner + "&page=%s", ResponseMatches.class)
                    .mapToInt(data -> data.team1goals)
                     .sum();
             final int team2Goals = Result.invokeAPI("https://jsonmock.hackerrank.com/api/football_matches?competition=" + competition + "&year=" + year + "&team2=" + winner + "&page=%s", ResponseMatches.class)
                    .mapToInt(data -> data.team2goals)
                    .sum();
             return team1Goals + team2Goals;
        }

        private static Gson GSON = new Gson();

        abstract class Response<Data> {
            int total_pages;

            List<Data> data;

            List<Data> getData() {
                return data;
            }
        }

        public static <DataEncapsulator> Stream<DataEncapsulator> invokeAPI(
                final String url,
                final Class<? extends Response<DataEncapsulator>> clazz) {
            final Response resp = invoke(String.format(url, 1), clazz);
            return Stream.concat(Stream.of(resp), IntStream.rangeClosed(2, resp.total_pages)
                    .parallel()
                    .mapToObj(i -> String.format(url, i))
                    .map(uri -> invoke(uri, clazz)))
                    .map(Response::getData)
                    .flatMap(List::stream);
        }

        public static <Response> Response invoke(final String url, final Class<Response> clazz) {
            final String finalUrl = url.replace(" ", "%20");
            final StringBuilder c = new StringBuilder();
            try {
                final HttpsURLConnection con = (HttpsURLConnection) new URL(finalUrl).openConnection();
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
            return GSON.fromJson(c.toString(), clazz);
        }
    }

    public static void main(String[] args) {
        System.out.println(HackerrankRestAPI.Result.getWinnerTotalGoals("UEFA Champions League", 2011));
    }
}
