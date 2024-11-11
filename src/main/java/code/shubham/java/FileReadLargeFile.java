package code.shubham.java;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;

public class FileReadLargeFile {
    /**
     * big file, read the file, search for a particular string, print the locatioin or entire line
     *
     * big file -> 2mb
     */

    // Time: O(), Space: O()
    private class Solution {

        Object solve(String filePath, String keyword, long skipBytes) {
            Long start = System.nanoTime();
            ArrayList<String> result = new ArrayList<>();
            StringBuilder line = new StringBuilder();

            try (FileInputStream fis = new FileInputStream(filePath);
                 FileChannel fileChannel = fis.getChannel()) {
                 fis.skip(skipBytes);

                 ByteBuffer buffer = ByteBuffer.allocate(1024); // Allocate 1KB buffer
                 while (fileChannel.read(buffer) > 0) {
                    buffer.flip(); // Switch to read mode
                    while (buffer.hasRemaining() ) {
                        // Process the data (e.g., print characters)
                        line.append((char) buffer.get());
                    }

                     buffer.clear();  // Clear for the next readindexOf

                     Arrays.stream(line.toString().split("\n"))
                             .filter(l -> l.contains(keyword))
                             .forEach(result::add);
                     line.setLength(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(String.format("TotalTime: %s", System.nanoTime() - start));
            return result;
        }
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        final var question = new FileReadLargeFile();

        final var solution = question.new Solution();

        System.out.println(solution.solve("/home/r00t/git/largefiles/6GB", "LAST_LINE", 0));
    }

}
