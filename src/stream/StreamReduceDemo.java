package stream;

import com.sun.xml.internal.rngom.digested.DValuePattern;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public class StreamReduceDemo {
    public static void main(String[] args) {
        Stream<Integer> stream1 = getIntegerStream();
        Integer result1 = stream1.reduce(0, (i, j) -> i + j);
        System.out.println("result1 = " + result1);

        Stream<Integer> stream2 = getIntegerStream();
        //stream2.reduce((a, b) -> a + b).ifPresent(System.out::println);
        Integer result2 = stream2.reduce(0, Integer::sum);
        System.out.println("result2 = " + result2);

        Stream<Integer> stream3 = getIntegerStream();
        Optional<Integer> result3 = stream3.reduce(Integer::max);
        result3.ifPresent(value -> System.out.println("result3 = " + value));
    }

    private static Stream<Integer> getIntegerStream() {
        return Arrays.stream(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9});
    }

    private static Integer[] getIntegerArray() {
        return new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
    }
}
