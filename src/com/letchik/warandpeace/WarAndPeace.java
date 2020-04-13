package com.letchik.warandpeace;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WarAndPeace {
    private static List<String> largestWords = new ArrayList<>();
    public static List<String> getLargestWordsSequential(String path){
        try {
            Stream<String> allWords = Pattern.compile("\\PL+").splitAsStream(Files.readString(Paths.get(path)));
            largestWords = fiveHundredWords(allWords);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return largestWords;
    }

    public static List<String> getLargestWordsParallel(String path){
        try {
            Stream<String> allWords = Pattern.compile("\\PL+").splitAsStream(Files.readString(Paths.get(path)));
            largestWords = fiveHundredWords(allWords.parallel());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return largestWords;
    }
    private static List<String> fiveHundredWords(Stream<String> stringStreams){
        largestWords = stringStreams
                .collect(Collectors.groupingBy(String::length))
                .entrySet().stream()
                .sorted(Map.Entry.<Integer, List<String>> comparingByKey().reversed())
                .map(Map.Entry::getValue)
                .flatMap(Collection::stream).distinct().limit(500).collect(Collectors.toList());
        return largestWords;
    }
}


