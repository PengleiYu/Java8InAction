package chapter5_6;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Created by yupenglei on 17/7/3.
 */
class Hello {
    public static void main(String[] args) {
        action();
    }

    /**
     * 生成勾股数
     */
    private static void action() {
        IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                        .filter(ints -> ints[2] % 1 == 0))

                .map(Arrays::toString)
                .forEach(System.out::println);
    }
}
