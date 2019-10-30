package stream;

import java.util.Arrays;
import java.util.List;

public class DishResource {
    private final List<Dish> menu;

    private static class DishResourceHolder {
        static final DishResource INSTANCE = new DishResource();
    }

    public static DishResource getInstance() {
        return DishResourceHolder.INSTANCE;
    }

    private DishResource() {
        menu = Arrays.asList(
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
    }

    public List<Dish> getMenu() {
        return menu;
    }
}
