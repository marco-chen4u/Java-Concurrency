package stream;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class CreateStreamDemo {
    public static void main(String[] args) {
        //createStreamFromCollection().forEach(System.out::println);
        //createStreamFromValues().forEach(System.out::println);
        //createStreamFromArrays().forEach(System.out::println);
        //createStreamFromIterator().forEach(System.out::println);
        //createStreamFromGenerate().forEach(System.out::println);
        createProductStreamFromGenerate().forEach(System.out::println);
    }

    private static Stream<String> createStreamFromCollection() {
        List<String> list = Arrays.asList("Candle", "Apple", "Orange", "Street", "Prawn", "Smoke", "Banana");
        return list.stream();
    }

    private static Stream<String> createStreamFromValues() {
        return Stream.of("Candle", "Apple", "Orange", "Street", "Prawn", "Smoke", "Banana");
    }

    private static Stream<String> createStreamFromArrays() {
        return Arrays.stream(new String[] {"Candle", "Apple", "Orange", "Street", "Prawn", "Smoke", "Banana"});
    }

    private static Stream<Integer> createStreamFromIterator() {
        Stream<Integer> stream = Stream.iterate(0, n -> n + 2).limit(20);
        return stream;
    }

    private static Stream<Double> createStreamFromGenerate() {
        return Stream.generate(Math::random).limit(10);
    }

    private static Stream<Product> createProductStreamFromGenerate() {
        return Stream.generate(new ProductSupplier()).limit(14);
    }

    // helper class
    static class ProductSupplier implements Supplier<Product> {
        // fields
        private int index = 0;
        private Random random = new Random(System.currentTimeMillis());

        @Override
        public Product get() {
            index = random.nextInt(1000);
            return new Product(index, "name->" + index);
        }
    }

    // helper class
    static class Product {
        // fields
        private int id;
        private String name;

        // constructor
        public Product(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Product{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
