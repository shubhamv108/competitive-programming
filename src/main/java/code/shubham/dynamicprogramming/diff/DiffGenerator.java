package code.shubham.dynamicprogramming.diff;

import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class DiffGenerator {

    private static final String DIFF_FILE_PATH = "src/main/generated/diff/%s.md";

    public static void main(String[] args) throws IOException {
        Path outputPath = Path.of(String.format(DIFF_FILE_PATH, System.currentTimeMillis()));
        Files.createDirectories(outputPath.getParent());
        Files.createFile(outputPath);
        DiffRowGenerator generator = DiffRowGenerator.create()
                .showInlineDiffs(true)
                .inlineDiffByWord(true)
                .oldTag(f -> "<font color=\"red\">")
                .newTag(f -> "<font color=\"green\">")
                .build();
        List<DiffRow> rows = generator.generateDiffRows(
                Arrays.asList("This is a test senctence.", "This is the second line.", "And here is the finish."),
                Arrays.asList("This is a test for diffutils.", "This is the second line."));

        Files.write(outputPath, "|original|new|\n".getBytes(), StandardOpenOption.APPEND);
        Files.write(outputPath, "|--------|---|\n".getBytes(), StandardOpenOption.APPEND);
        for (DiffRow row : rows) {
            String next = "|" + row.getOldLine() + "|" + row.getNewLine() + "|" + "\n";
            Files.write(outputPath, next.getBytes(), StandardOpenOption.APPEND);
        }
    }
}
