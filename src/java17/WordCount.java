package java17;

import java.util.*;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

public class WordCount {

    public static void main(String[] args) {
        List<String> l = List.of("abc", "acb", "abc", "acb", "abc");
        Map<String, LongAdder> map = new HashMap();
        for (String word : l) {
            map.computeIfAbsent(word, k -> new LongAdder()).increment();
        }
        map.forEach((k, v) -> System.out.println(k + "->"+v));
        Integer[] values = new Integer[] {3 , 4, 7, 8, 11, 23};
        List<Integer> asList = Arrays.asList(values);
        int result = asList.stream().reduce(0,
                (partialResult , value)  ->
                {
                    System.out.println("Partial result :: " + partialResult);
                    System.out.println("Value :: " + value);
                    return partialResult + value;
                });
        System.out.println("Total is :: "+ result);
        System.out.println(map);
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        int sum1 = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum);
        System.out.println(sum1);

        Stream<Long> oddNaturalNumbers = Stream.iterate(1L, n -> n + 2);
        Stream.iterate(1L, n -> n + 2).limit(5).forEach(System.out::println);
        Stream.generate(Math::random).limit(5).forEach(System.out::println);

        int oddSquareSum = IntStream.of(1, 2, 3, 4, 5)
                .peek(e -> System.out.println("Taking integer: " + e))
                .filter(n -> n % 2 == 1)
                .peek(e -> System.out.println("Filtered integer: " + e))
                .map(n -> n * n)
                .peek(e -> System.out.println("Mapped integer: " + e))
                .reduce(0, Integer::sum);
        System.out.println("Odd square sum = " + oddSquareSum);

        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        double bill = costBeforeTax.stream().map((cost) -> cost + 0.12*cost).reduce((sum2, cost) -> sum2 + cost).get();
        double bill1 = costBeforeTax.stream().map((cost) -> cost + 0.12*cost).reduce(0.0d, Double::sum);
        System.out.println("Total : " + bill+" bill2 "+bill1);

        List<String> artists = List.of("Amir", "Shahrukh", "Sachin", "Manoj Bajpeyi");
        System.out.println(artists.stream()
                .collect(Collectors.maxBy(comparing(String::length)))
                .orElseThrow(RuntimeException::new));

    }


}
