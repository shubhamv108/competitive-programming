package code.shubham.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileRead {
    /**
     * big file, read the file, search for a particular string, print the locatioin or entire line
     *
     * big file -> 2mb
     */

    // Time: O(), Space: O()
    private class Solution {

        Object solve(String filePath, String keyword) throws IOException {
            ArrayList<String> result = new ArrayList<>();

            File file = new File(filePath);
            if (!file.exists())
                return result;

            try (InputStream inputStream = new FileInputStream(file);
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        if (line.contains(keyword))
                            result.add(line);
                    }
            }

            return result;
        }
    }

    private class Solution2 {

        Object solve(String filePath, String keyword) throws IOException {
            ArrayList<String> result = new ArrayList<>();
            StringBuilder line = new StringBuilder();

            try (FileInputStream fis = new FileInputStream(filePath);
                 FileChannel fileChannel = fis.getChannel()) {

                 ByteBuffer buffer = ByteBuffer.allocate(1024); // Allocate 1KB buffer
                 while (fileChannel.read(buffer) > 0) {
                    buffer.flip(); // Switch to read mode
                    while (buffer.hasRemaining()) {
                        byte byt = buffer.get();
                        if (((char) byt) == '\r')
                            continue;
                        if (((char) byt) == '\n')
                            break;
                        // Process the data (e.g., print characters)
                        line.append((char) byt);
                    }

                    if (line.toString().contains(keyword))
                        result.add(line.toString());
                     buffer.clear();  // Clear for the next readindexOf

                    line.setLength(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }
    }

    private class Solution3 {

        Object solve(String filePath, String keyword) throws IOException {
            return Files.lines(Paths.get(filePath)).anyMatch(line -> line.contains(keyword));
        }
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        final var question = new FileRead();

//        final var solution = question.new Solution();
        final var solution = question.new Solution2();

        System.out.println(solution.solve("/home/r00t/git/largefiles/6GB", "LAST_LINE"));
    }

}
