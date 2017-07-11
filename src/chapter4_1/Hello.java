package chapter4_1;

/**
 * Created by yupenglei on 17/7/3.
 */
class Hello {
    public static void main(String[] args) {
        Dish.Companion.getList().stream()
                .filter(dish -> dish.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .forEach(System.out::println);
    }
}
