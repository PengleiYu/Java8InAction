package chapter5_2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by JJBOOM on 2017/7/2.
 */
class Hello {
    public static void main(String[] args) {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        numbers1.stream()
                .flatMap(i -> numbers2.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j}))
                .map(Arrays::toString)
                .forEach(System.out::println);
    }
}
