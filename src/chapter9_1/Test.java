package chapter9_1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yupenglei on 17/7/11.
 */
class Test {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(4, 2, 5, 7, 3, 1, 32, 11);
        List<String> collect = integers.stream()
                .map(String::valueOf)
                .map(Integer::valueOf)
                .sorted(Comparator.naturalOrder())
                .map(String::valueOf)
                .collect(Collectors.toList());
        System.out.println(collect);

    }
}
