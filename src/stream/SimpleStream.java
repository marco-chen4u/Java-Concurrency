package stream;

import java.util.*;
import java.util.stream.Collectors;

public class SimpleStream {
    public static void main(String[] args) throws InterruptedException {
        List<Dish> menu = getMenu();

        List<String> dishNameByCollections = getDishNameByCollections(menu);
        System.out.println(dishNameByCollections);

        /*List<String> dishNameByStream = getDishNameByStream(menu);
        System.out.println(dishNameByStream);*/

        List<String> dishNameByParallelStream = getDishNameByParallelStream(menu);
        System.out.println(dishNameByParallelStream);

    }

    private static List<Dish> getMenu() {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fires", true, 530, Dish.Type.OTHERS),
                new Dish("rice", true, 350, Dish.Type.OTHERS),
                new Dish("season fruit", true, 120, Dish.Type.OTHERS),
                new Dish("pizza", true, 550, Dish.Type.OTHERS),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH),
                new Dish("cod", false, 480, Dish.Type.FISH)
        );

        return menu;
    }

    private static List<String> getDishNameByCollections(List<Dish> menu) {
        List<String> result = new ArrayList<String>();

        // filter and get calories < 400
        List<Dish> lowCalories = new ArrayList<Dish>();
        for (Dish dish : menu) {
            if (dish.getCalories() < 400) {
                lowCalories.add(dish);
            }
        }

        // sort
        Collections.sort(lowCalories, (a, b)->Integer.compare(a.getCalories(), b.getCalories()));

        for (Dish dish : lowCalories) {
            result.add(dish.getName());
        }

        return result;
    }

    private static List<String> getDishNameByStream(List<Dish> menu) {
        return menu.stream().filter(dish -> dish.getCalories() < 400)
                    //.sorted(Comparator.comparing((dish)->dish.getCalories()))
                    .sorted(Comparator.comparing(Dish::getCalories))
                    .map(Dish::getName)
                    .collect(Collectors.toList());
    }

    private static List<String> getDishNameByParallelStream(List<Dish> menu) {
        return menu.parallelStream().filter(dish -> {
                    try{
                        Thread.sleep(10000);
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    return dish.getCalories() < 400;})
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
    }
}
