package chapter5_7;

import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by yupenglei on 17/7/3.
 */
class Hello {
    public static void main(String[] args) {
        action3();
    }

    /**
     * generate生成斐波那契数列
     */
    private static void action3() {
        IntSupplier intSupplier = new IntSupplier() {
            private int pre = 1, current = 1;

            @Override
            public int getAsInt() {
                int old = pre;
                int next = pre + current;
                pre = current;
                current = next;
                return old;
            }
        };
        IntStream.generate(intSupplier)
                .limit(10)
                .forEach(System.out::println);
    }

    /**
     * generate
     */
    private static void action2() {
        Stream.generate(Math::random)
                .limit(10)
                .forEach(System.out::println);
    }

    /**
     * iterate生成斐波那契数列
     */
    private static void action1() {
        Stream.iterate(new int[]{1, 1}, ints -> new int[]{ints[1], ints[0] + ints[1]})
                .limit(10)
                .mapToInt(ints -> ints[0])
//                .map(Arrays::toString)
                .forEach(System.out::println);
    }
}
