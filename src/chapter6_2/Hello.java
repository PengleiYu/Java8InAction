package chapter6_2;

import chapter4_1.Dish;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by yupenglei on 17/7/3.
 */
class Hello {
    private static List<Dish> sDishList = Dish.Companion.getList();

    public static void main(String[] args) {

        Optional<Dish> mostCalorieDish = sDishList.stream()
                .max(Comparator.comparing(Dish::getCalories));
        double avgCalories = sDishList.stream()
                .collect(Collectors.averagingDouble(Dish::getCalories));
        DoubleSummaryStatistics summaryStatistics = sDishList.stream()
                .collect(Collectors.summarizingDouble(Dish::getCalories));
        int totalCalories = sDishList.stream()
//                .collect(Collectors.reducing(0,Dish::getCalories,(a,b)->a+b));
                .map(Dish::getCalories).reduce(0, (a, b) -> a + b);
        String shortMenu = sDishList.stream().map(Dish::getName).reduce((s1, s2) -> s1 + s2)
                .orElseGet(null);

        System.out.println("mostCalorieDish:" + mostCalorieDish.orElseGet(null));
        System.out.println("avgCalories:" + avgCalories);
        System.out.println("summaryStatistics:" + summaryStatistics);
    }
}
