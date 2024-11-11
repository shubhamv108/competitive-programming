package code.shubham.java;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileGenerator {
    private class Solution {
        Object solve(String filePath, String line, String lastLine, int repeatTimes) throws IOException {
            Path path = Paths.get(filePath);
            Files.createDirectories(path.getParent());
            Files.createFile(path);

            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                for (int i = 0; i < repeatTimes; ++i) {
                    outputStream.write(line.getBytes());
                    outputStream.flush();

                    if (Files.size(path) > 6368709120l) {
                        outputStream.write(lastLine.getBytes());
                        outputStream.flush();
                        break;
                    }
                }
            }
            return null;
        }
    }

    public static void main(String[] args) throws IOException {
        new FileGenerator().new Solution().solve("/home/r00t/git/largefiles/6GB", "test data line\n", "LAST_LINE", Integer.MAX_VALUE - 9);
    }
}
