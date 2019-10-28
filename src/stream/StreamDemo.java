package stream;

import java.util.ArrayList;
import java.util.List;

public class StreamDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("abc");
        list.add("bcd");
        list.add("ddd");
        list.add("f");
        list.add("hello");

        //Optional<String> max = list.stream().max(String::compareTo);
        //System.out.println(max.get());//terminal stream manipulation

        //list.stream().sorted().forEach(item -> System.out.println(item));//median stream manipulation

        System.out.println(list.stream().distinct().count());//median stream

        /***
         * for more to learn
         * (1)geeksforgeeks.org/streams-arrays-java-8
         * (2)docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html
         */

    }
}
