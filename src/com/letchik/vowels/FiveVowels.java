package com.letchik.vowels;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FiveVowels {
    public static List<String> findWordsWithVowels(String path) {
        List<String> wordsWithVowels = new ArrayList<>();
        try {
            Stream<String> words = Pattern.compile("\\PL+").splitAsStream(Files.readString(Paths.get(path)));
            wordsWithVowels = words.filter(FiveVowels::stringHasVowels).collect(Collectors.toList());;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordsWithVowels;
    }

    private static boolean stringHasVowels(String str) {
        int count = 0;
        for (int i = 1; i < str.length() - 1; i++) {
            if (isVowel(str.charAt(i)) && !isVowel(str.charAt(i + 1)) && !isVowel(str.charAt(i - 1))) {
                count++;
            }
        }
        if (str.length()>0 && (isVowel(str.charAt(str.length() - 1))))
            count++;
        if (str.length()>0 && isVowel(str.charAt(0)))
            count++;
        return count == 5;
    }

    private static boolean isVowel(char c) {
        c = Character.toLowerCase(c);
        return c == 'a' || c == 'e' || c == 'i' ||
                c == 'o' || c == 'u';
    }
}
