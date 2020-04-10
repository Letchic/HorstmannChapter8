package com.letchik.flatmapping;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UseFlatMappingMethod {
    public static void  use() {
        List<Optional<String>> listOfOptionals = Arrays.asList(
                Optional.of("First element"), Optional.empty(), Optional.of("Third element"),Optional.empty());
        List<String> filteredList = listOfOptionals.stream().collect(Collectors.flatMapping(Optional::stream, Collectors.toList()));
        System.out.println(filteredList);
    }
}
