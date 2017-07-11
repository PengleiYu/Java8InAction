package chapter6_6;

import chapter6_4.Hello;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;

/**
 * Created by yupenglei on 17/7/11.
 */
public class CollectorHarness {
    public static void main(String[] args) {
        long fastest = Long.MAX_VALUE;
        int count = 1_000_000;
        long cost = 0;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            Hello.partitionPrimes(count);
//            CustomCollector.partitionPrimesWithCustomCollector(count);
            long duration = (System.nanoTime() - start) / 1_000_000;
            cost += duration;
            if (duration < fastest) {
                fastest = duration;
            }
        }
        System.out.println(String.format("faster=%s,avg=%s", fastest, cost / 10));
    }

//    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static void test() {
        Supplier<String> supplier = () -> "";
        BiConsumer<String, String> acc = String::concat;
        BiConsumer<String, String> combiner = String::concat;
        IntStream.rangeClosed(2, 1000).boxed()
                .map(String::valueOf)
                .collect( () -> "", String::concat, String::concat
//                        supplier, acc, combiner
                );


        UnaryOperator<String> header=(String s)->s;
        UnaryOperator<String> then=(String s)->s;
        Function<String, String> stringStringFunction = header.andThen(then);
    }
}
