package stream;

import java.util.Arrays;
import java.util.stream.Stream;

public class StreamMatchDemo {
    public static void main(String[] args) {
        Stream<Integer> stream1 = getIntegerStream();
        boolean matchResult1 = stream1.allMatch(i -> i > 2);
        System.out.println("allMatch(i > 2) = " + matchResult1);

        Stream<Integer> stream2 = getIntegerStream();
        boolean matchResult2 = stream2.anyMatch(i -> i > 7);
        System.out.println("anyMatch(i > 7) = " + matchResult2);

        Stream<Integer> stream3 = getIntegerStream();
        boolean matchResult3 = stream3.noneMatch(i -> i < 0);
        System.out.println("noneMatch(i < 0) = " + matchResult3);
    }

    private static Stream<Integer> getIntegerStream() {
        return Arrays.stream(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9});
    }
}
