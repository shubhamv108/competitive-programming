package code.shubham.api;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Solution {

    int getNumberOfMovies(String substr) {
        String endpoint = "https://jsonmock.hackerrank.com/api/moviesdata/search/?Title=%s";

        HackerEarthAPIInvoker<Boolean> apiInvoker = new HackerEarthAPIInvoker<>(endpoint);

        Function<Data, Boolean> resultDataExtractor = data ->
              data.getTitle().contains(substr);

        List<Boolean> result = apiInvoker.invoke(resultDataExtractor, substr);
        return (int) result.stream().filter(e -> e).count();

    }

}
