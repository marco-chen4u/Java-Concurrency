package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamFilterDemo {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 6, 7, 7, 8, 9, 1);
        List<Integer> collect = list.stream().filter(i -> i % 2 == 0)
                .collect(Collectors.toList());
        System.out.println(collect);

        List<Integer> result = list.stream().distinct().collect(Collectors.toList());
        System.out.println(result);

        List<Integer> trunckBy5 = list.stream().skip(5).collect(Collectors.toList());
        System.out.println(trunckBy5);

        List<Integer> first5 = list.stream().limit(5).collect(Collectors.toList());
        System.out.println(first5);
    }
}
