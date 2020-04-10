package com.letchik.average;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class AvgWithReduce {
    public static double getAvg( Stream<Double> rnd) {
        final AtomicInteger count = new AtomicInteger();

        Double avg = rnd.reduce(0.0, (x, y) -> {
            int number = count.incrementAndGet();
            return (x * (number - 1) + y) / number;
        });
        return avg;
    }
}
