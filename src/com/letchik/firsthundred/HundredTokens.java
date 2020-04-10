package com.letchik.firsthundred;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HundredTokens {
    public static Stream<String> findTokens(String path) {
        Stream<String> tokens = Stream.empty();
        try {
            Stream<String> words = Pattern.compile(" ").splitAsStream(Files.readString(Paths.get(path)));
            tokens = words.filter(HundredTokens::isToken).limit(100);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return tokens;
    }
    private static boolean isToken(String s) {
        return s.codePoints().allMatch(Character::isAlphabetic);
    }

    public static List<String> mostFrequentWords(String path) {
        List<String> res = new ArrayList<>();
        try {
            Stream<String> words = Pattern.compile("\\PL+").splitAsStream(Files.readString(Paths.get(path)));
            Map<String, Integer> frequentCount = words.collect(Collectors.toMap(String::toLowerCase, w -> 1, Integer::sum));
            res = frequentCount.entrySet().stream()
                    .sorted((x, y) -> y.getValue() == x.getValue()
                            ? x.getKey().compareTo(y.getKey())
                            : (y.getValue() - x.getValue()))
                    .limit(10).map(Map.Entry::getKey)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

}
