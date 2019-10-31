package stream;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamFindDemo {
    public static void main(String[] args) {
        Stream<Integer> stream1 = getIntegerStream();
        Optional<Integer> optional1 = stream1.filter(i -> i % 2 == 0).findAny();
        System.out.println(optional1.get());

        Stream<Integer> stream2 = getIntegerStream();
        Optional<Integer> optional2 = stream2.filter(i -> i % 2 == 0).findFirst();
        //System.out.println(optional2.get());
        //optional2.ifPresent(System.out::println);
        optional2.ifPresent(i->System.out.println("optional2.get() = " + i));

        int result3 = find(getIntegerArray(), -1, value -> value > 1000);
        System.out.println(result3);

        Stream<Integer> stream4 = getIntegerStream();
        Optional<Integer> optional4 = stream4.filter(i -> i > 9).findAny();
        System.out.println(optional4.orElse(Integer.MIN_VALUE));
    }

    private static int find(Integer[] values, int defaultValue, Predicate<Integer> predicate) {
        for (int value : values) {
            if (predicate.test(value)) {
                return value;
            }
        }

        return defaultValue;
    }

    private static Stream<Integer> getIntegerStream() {
        return Arrays.stream(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9});
    }

    private static Integer[] getIntegerArray() {
        return new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
    }
}
