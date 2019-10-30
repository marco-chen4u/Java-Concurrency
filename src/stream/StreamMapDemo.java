package stream;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamMapDemo {
    public static void main(String[] args) {
        //[1]map example
        /*List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 6, 7, 7, 8, 9, 1);

        List<Integer> result = list.stream().map(i -> i * 2).collect(Collectors.toList());
        System.out.println(result);

        List<Dish> dishList = DishResource.getInstance().getMenu();
        dishList.stream().map(dish -> dish.getName()).forEach(System.out::println);
        List<String> dishs = dishList.stream().map(dish -> dish.getName()).collect(Collectors.toList());
        System.out.println(dishs);*/

        //[2]flatmap example
        System.out.println("before streaming");
        System.out.println(Arrays.toString(getWords()));
        System.out.println("after streaming");
        Stream<String[]> streamOfWordCharStringArray = Arrays.stream(getWords()).map(word -> word.split(""));
        Stream<String> stringStream = streamOfWordCharStringArray.flatMap(Arrays::stream);
        stringStream.forEach(System.out::print);
        System.out.println();

        System.out.println("----removing the duplicate characters----");
        Stream<String[]> streamOfWordCharStringArray2 = Arrays.stream(getWords()).map(word -> word.split(""));
        Stream<String> stringStream2 = streamOfWordCharStringArray2.flatMap(Arrays::stream);
        stringStream2.distinct().forEach(System.out::print);
        System.out.println();
        System.out.println("done!");

    }

    private static String[] getWords() {
        String[] words = new String[] {"All", "things", "are", "difficult", "before", "they", "are", "easy"};
        return words;
    }
}
