package stream;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumericStreamDemo {
    public static void main(String[] args) {
        Stream<Integer> stream1 = getIntegerStream();
        Optional<Integer> result1 = stream1.filter(i -> i > 3).reduce(Integer::sum);
        System.out.println("result1 = " + result1.get());

        IntStream stream2 = getIntegerStream().mapToInt(i -> i.intValue());
        //int result2 = stream2.filter(i -> i > 4).reduce(0, (i, j) -> i + j);
        int result2 = stream2.filter(i -> i > 4).sum();
        System.out.println("result2 = " + result2);

        int value = 9;
        IntStream stream3 = IntStream.rangeClosed(1, 100).filter(i -> Math.sqrt(i * i + value * value) % 1 == 0);
        stream3.forEach(System.out::println);

        /*IntStream.rangeClosed(1, 100)
                .filter(i -> Math.sqrt(i * i + value * value) % 1 == 0)
                .boxed()
                .map(j -> new int[]{value, j, (int) Math.sqrt(value * value + j * j)})
                .forEach(nums-> System.out.println("a = " + nums[0] + ", b = " + nums[1] + ", c = " + nums[2]));*/

        IntStream.rangeClosed(1, 100)
                .filter(i -> Math.sqrt(i * i + value * value) % 1 == 0)
                .mapToObj(j -> new int[] {value, j, (int)Math.sqrt(value * value + j * j)})
                .forEach(nums -> System.out.println("a = " + nums[0] + ", b = " + nums[1] + ", c = " + nums[2]));


    }

    private static Stream<Integer> getIntegerStream() {
        return Arrays.stream(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9});
    }

    private static Integer[] getIntegerArray() {
        return new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
    }
}
