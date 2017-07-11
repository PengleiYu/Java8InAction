package chapter6_3;

import chapter4_1.Dish;

import static java.util.Comparator.*;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;


/**
 * Created by yupenglei on 17/7/3.
 */
class Hello {
    private enum CaloricLevel {DIET, NORMAL, FAT}

    private static List<Dish> sDishList = Dish.Companion.getList();

    public static void main(String[] args) { action3(); }


    /**
     * groupingBy的第二个参数可以是任意类型的搜集器
     */
    private static void action3() {
        Map<Dish.Type, Long> typeCount = sDishList.stream()
                .collect(groupingBy(Dish::getType, counting()));
        System.out.println("按类型计数：" + typeCount);

        Map<Dish.Type, Optional<Dish>> mostCalorieByType = sDishList.stream()
                //按组搜集
                .collect(groupingBy(Dish::getType,
                        //对每个分组流再进行搜集
                        maxBy(comparing(Dish::getCalories))));
        System.out.println("按类型最大热量：" + mostCalorieByType);

        //搜集结果转换为另一种类型
        Map<Dish.Type, Dish> mostCalorieByType2 = sDishList.stream()
//                .collect(toMap(Dish::getType, Function.identity(), BinaryOperator
//                        .maxBy(comparing (Dish::getCalories))));
                .collect(groupingBy(Dish::getType, collectingAndThen(maxBy(comparing
                        (Dish::getCalories)), Optional::get)));
        System.out.println("按类型最大热量: " + mostCalorieByType2);

        Map<Dish.Type, Integer> sumCalorieByType = sDishList.stream()
                .collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));
        System.out.println("按类型总热量: " + sumCalorieByType);

        Map<Dish.Type, Set<CaloricLevel>> calorieLevelByType = sDishList.stream()
                .collect(groupingBy(Dish::getType, mapping((Dish dish) -> {
                            if (dish.getCalories() < 400) {
                                return CaloricLevel.DIET;
                            } else if (dish.getCalories() < 700) {
                                return CaloricLevel.NORMAL;
                            } else return CaloricLevel.FAT;
                        },
//                        toSet()
                        toCollection(HashSet::new)//指定集合类型
                )));
        System.out.println("按类型热量类型: " + calorieLevelByType);
    }

    /**
     * 二级分组
     */
    private static void action2() {
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> map = sDishList.stream()
                .collect(groupingBy(Dish::getType, groupingBy(dish -> {
                    if (dish.getCalories() <= 400) {
                        return CaloricLevel.DIET;
                    } else if (dish.getCalories() <= 700) {
                        return CaloricLevel.NORMAL;
                    } else return CaloricLevel.FAT;
                })));
        System.out.println(map);
    }

    /**
     * 按Calorie划分三个等级
     */

    private static void action1() {
        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = sDishList.stream()
                .collect(groupingBy(dish -> {
                    if (dish.getCalories() <= 400) {
                        return CaloricLevel.DIET;
                    } else if (dish.getCalories() <= 700) {
                        return CaloricLevel.NORMAL;
                    } else return CaloricLevel.FAT;
                }));
        System.out.println(dishesByCaloricLevel);
    }
}
