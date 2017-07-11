package chapter6_5;

import chapter4_1.Dish;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Created by yupenglei on 17/7/6.
 */
class Hello {
    private static List<Dish> sDishList = Dish.Companion.getList();

    public static void main(String[] args) {
        List<Dish> collect1 = sDishList.stream().collect(new ToListCollector<>());
        ArrayList<Object> collect2 = sDishList.stream().collect(ArrayList::new, List::add,
                List::addAll);
        System.out.println(sDishList);
        System.out.println(collect1);
        System.out.println(collect2);
    }

    /**
     * 自定义的搜集器
     * @param <T>
     */
    private static class ToListCollector<T> implements Collector<T, List<T>, List<T>> {
        @Override
        public Supplier<List<T>> supplier() {
            return ArrayList::new;
        }

        @Override
        public BiConsumer<List<T>, T> accumulator() {
            return List::add;
        }

        @Override
        public BinaryOperator<List<T>> combiner() {
            return (list1, list2) -> {
                list1.addAll(list2);
                return list1;
            };
        }

        @Override
        public Function<List<T>, List<T>> finisher() {
            return Function.identity();
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH,
                    Characteristics.CONCURRENT));
        }
    }
}
