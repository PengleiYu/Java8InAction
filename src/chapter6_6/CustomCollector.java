package chapter6_6;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.IntStream;

/**
 * Created by yupenglei on 17/7/6.
 */
class CustomCollector {
    public static void main(String[] args) {
        Map<Boolean, List<Integer>> collect = partitionPrimesWithCustomCollector(100);
        System.out.println(collect);
    }

    public static Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(new PrimeNumbersCollector());
    }

    private static class PrimeNumbersCollector implements
            Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {

        @Override
        public Supplier<Map<Boolean, List<Integer>>> supplier() {
            return () -> new HashMap<Boolean, List<Integer>>() {
                {
                    put(true, new ArrayList<>());
                    put(false, new ArrayList<>());
                }
            };
        }

        @Override
        public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
            return (acc, candidate) -> acc.get(isPrime(acc.get(true), candidate)).add(candidate);
        }

        @Override
        public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
            return (left, right) -> {
                left.get(true).addAll(right.get(true));
                left.get(false).addAll(right.get(false));
                return left;
            };
        }

        @Override
        public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
            return Function.identity();
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
        }

        /**
         * 判定候选数是否是质数
         * <p>
         * 若列表中质数均不是候选数的因子，则候选数是质数
         */
        private static boolean isPrime(List<Integer> primes, int candidate) {
            int candidateRoot = (int) Math.sqrt(candidate);
            return takeWhile(primes, i -> i <= candidateRoot).stream()//只选取不大于候选数平方根的质数序列
                    .noneMatch(integer -> candidate % integer == 0);
        }

        /**
         * 实时执行
         * 非实时的可以和nonMatch组合执行
         */
        private static <A> List<A> takeWhile(List<A> list, Predicate<A> predicate) {
            int i = 0;
            for (A item : list) {
                if (!predicate.test(item)) {
                    return list.subList(0, i);
                }
                i++;
            }
            return list;
        }
    }
}
