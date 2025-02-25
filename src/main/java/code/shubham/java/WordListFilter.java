package code.shubham.java;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.IntStream;

sealed interface Dictionary permits SetDictionary, TrieDictionary  {
    void add(String w, int i);
    boolean contains(String w, int i, int lastIndexExcluded);
}

non-sealed class SetDictionary implements Dictionary {
    private final Set<String> words = new TreeSet<>();

    @Override
    public void add(String w, int i) {
        words.add(w.substring(i));
    }

    @Override
    public boolean contains(String w, int i, int lastIndexExcluded) {
        return words.contains(w.substring(i, lastIndexExcluded));
    }
}

non-sealed class TrieDictionary implements Dictionary {
    private final Map<Character, TrieDictionary> next = new HashMap<>();
    private boolean isEOW;

    public void add(String w, int i) {
        if (i == w.length()) {
            isEOW = true;
            return;
        }

        next.computeIfAbsent(w.charAt(i), __ -> new TrieDictionary())
                .add(w, i + 1);
    }

    public boolean contains(String w, int i, int lastIndexExcluded) {
        if (i == lastIndexExcluded)
            return isEOW;

        char nextIdx = w.charAt(i);
        return next.get(nextIdx) != null && next.get(nextIdx).contains(w, i + 1, lastIndexExcluded);
    }
}

public class WordListFilter {

    private final Dictionary dictionary;
    private final Collection<String> words = new HashSet<>();

    public WordListFilter(
            String filePath,
            Dictionary dictionary,
            Predicate<String> dictionaryLoader,
            Predicate<String> collectorPredicate) throws IOException {
        this.dictionary = dictionary;

        try (InputStream inputStream = new FileInputStream(filePath);
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String nextLine = null;
            while ((nextLine = br.readLine()) != null) {
                Arrays.stream(nextLine.split("\\s"))
                        .peek(word -> {
                            if (dictionaryLoader.test(word))
                                dictionary.add(word, 0);
                        })
                        .filter(collectorPredicate)
                        .forEach(words::add);
            }
        }
    }

    public Collection<String> filter() {
        return words.stream()
                .filter(word -> IntStream.range(1, word.length() - 1)
                        .anyMatch(i -> dictionary.contains(word, 0, i) && dictionary.contains(word, i, word.length())))
                .toList();
    }

    public static void main(String[] args) throws IOException {
        System.out.println(
                new WordListFilter(
                        "src/main/resource/wordList.txt",
                        new TrieDictionary(),
                        word -> word.length() < 6,
                        word -> word.length() == 6)
                        .filter()
                        .size());
    }
}
