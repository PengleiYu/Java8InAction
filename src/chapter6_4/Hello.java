package chapter6_4;

import chapter4_1.Dish;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

/**
 * Created by yupenglei on 17/7/6.
 */
public class Hello {
    private static List<Dish> sDishList = Dish.Companion.getList();

    public static void main(String[] args) {
        System.out.println(partitionPrimes(100));
    }

    private static void action2() {
        Predicate<Integer> isPrime = i ->
                IntStream.rangeClosed(2, (int) Math.sqrt(i)).noneMatch(j -> i % j == 0);

        Map<Boolean, List<Integer>> partitionPrime = IntStream.rangeClosed(2, 100).boxed()
                .collect(partitioningBy(isPrime));
        System.out.println("partitionPrime: " + partitionPrime);
    }

    public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(partitioningBy(Hello::isPrime));
    }

    private static boolean isPrime(int candidate) {
        return IntStream.rangeClosed(2, (int) Math.sqrt(candidate))
                .noneMatch(i -> candidate % i == 0);
    }

    /**
     * 分区函数
     */
    private static void action1() {
        Map<Boolean, List<Dish>> partitionedMenu = sDishList.stream()
                .collect(partitioningBy(Dish::getVegetarian));
        System.out.println("partitionedMenu:" + partitionedMenu);
        Map<Boolean, List<Dish>> partitionedMenu2 = sDishList.stream()
                .collect(groupingBy(Dish::getVegetarian));
        System.out.println("partitionedMenu2:" + partitionedMenu2);
        Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType = sDishList.stream()
                .collect(partitioningBy(Dish::getVegetarian, groupingBy(Dish::getType)));
        System.out.println("vegetarianDishesByType:" + vegetarianDishesByType);
        Map<Boolean, Dish> mostCaloriePartitionedByVegetarian = sDishList.stream()
                .collect(partitioningBy(Dish::getVegetarian,
                        collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)));
        System.out.println("mostCaloriePartitionedByVegetarian: " +
                mostCaloriePartitionedByVegetarian);
    }
}