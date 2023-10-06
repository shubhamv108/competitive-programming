package code.shubham.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class SearchStringInTextFile {

    public boolean isWordPresent(final String filePath, final String w) throws IOException {
//        final var path = Paths.get(filePath);
//        if (!Files.exists(path))
//            throw new IllegalArgumentException(String.format("No file path exists with path: %s", filePath));
        return Files.lines(Paths.get(filePath))
                .flatMap(line -> Stream.of(line.split("\\s")))
                .anyMatch(w::equals);
    }

    public static void main(String[] args) throws IOException {
        System.out.println(
            new SearchStringInTextFile().
                    isWordPresent(
                "/home/shubham/git/shubham/competitive-programming/src/main/java/code/shubham/java/SearchStringInTextFile.java",
                    "SearchStringInTextFile")
        );
    }
}
