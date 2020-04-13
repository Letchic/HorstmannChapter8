package com.letchik;

import com.letchik.average.AvgWithReduce;
import com.letchik.firsthundred.HundredTokens;
import com.letchik.flatmapping.UseFlatMappingMethod;
import com.letchik.prime.PrimeNumbers;
import com.letchik.vowels.FiveVowels;
import com.letchik.warandpeace.WarAndPeace;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class Main {
    public static void main(String[] args) {

       //Ex 7
        List<String> tokens= HundredTokens.mostFrequentWords("1.txt");
        tokens.forEach(System.out::println);

        //Ex 8
        UseFlatMappingMethod.use();

        //Ex9
        List<String> wordsWithVowels = FiveVowels.findWordsWithVowels("1.txt");
        wordsWithVowels.forEach(System.out::println);

        //Ex10
        List<String> str = new ArrayList<>();
        Collections.addAll(str,   "one", "two", "three", "four",
                                            "five", "six", "seven", "eight",
                                            "nine", "ten", "eleven", "twelve");

        double length = str.stream().mapToInt((s) -> s.length()).average().orElse(0.0);
        System.out.println(length);

        //Ex11
        List<String> res = str.stream().collect(Collectors.groupingBy(String::length))
                .entrySet().stream().max(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue).orElse(null);
        res.forEach(System.out::println);

        //Ex15
        Stream<Double> rnd = Stream.generate(Math::random).limit(1000);
        System.out.println(AvgWithReduce.getAvg(rnd));

        //Ex16
        PrimeNumbers.findPrimeNumbers();

        //Ex17
        List<String> largestWords = new ArrayList<>();

        long timeS = System.currentTimeMillis();
        WarAndPeace.getLargestWordsSequential("Война и мир. Том 1.txt");
        timeS=System.currentTimeMillis()-timeS;

        long timeP = System.currentTimeMillis();
        largestWords = WarAndPeace.getLargestWordsParallel("Война и мир. Том 1.txt");
        timeP=System.currentTimeMillis()-timeP;

        for(String s:largestWords){
            System.out.println(s);
        };

        System.out.println("Sequential time " + timeS + " ns");
        System.out.println("Parallel time   " + timeP + " ns");
    }
}
