package com.letchik.prime;

import java.math.BigInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PrimeNumbers {
    public static void findPrimeNumbers() {
        String bignumber = Stream.iterate("1", n -> n + "0").limit(50).reduce((s1, s2) -> s2).orElse("");

        long timeS = System.currentTimeMillis();
        Stream<BigInteger> integers = Stream.iterate(new BigInteger(bignumber), n -> n.add(BigInteger.ONE)).filter(b -> b.isProbablePrime(1)).limit(500);
        timeS = System.currentTimeMillis() - timeS;

        long timeP = System.currentTimeMillis();
        Stream<BigInteger> integers2 = Stream.iterate(new BigInteger(bignumber), n -> n.add(BigInteger.ONE)).filter(b -> b.isProbablePrime(1)).limit(500).parallel();
        timeP = System.currentTimeMillis() - timeP;

        integers.collect(Collectors.toList()).
                forEach(System.out::println);

        System.out.println("Sequential " + timeS + "ns");
        System.out.println("Sarallel   " + timeP + "ns");
    }
}
