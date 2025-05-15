package org.tony.stockquotes;


import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestLambda extends TestCase {


    @Test
    public void test01() {
        List<String> lines = Arrays.asList("xxxx,cccc,ddddyyyy,ddddd", "dddd,ccccc,dddd,yyyy");
        long i = lines.stream().flatMap(line -> Arrays.stream(line.split(",")).flatMap(word -> Arrays.stream(word.split("")))).count();//forEach(System.out::println);
        System.out.println(i);
        lines.stream().flatMap(line -> Arrays.stream(line.split(",")).flatMap(word -> Arrays.stream(word.split("")))).collect(Collectors.groupingBy(x -> x, Collectors.counting())).entrySet().forEach(System.out::println);
        lines.stream().map(x -> x.toUpperCase()).forEach(System.out::println);
    }

}
