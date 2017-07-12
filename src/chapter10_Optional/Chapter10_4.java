package chapter10_Optional;

import java.util.Optional;
import java.util.Properties;

/**
 * Created by yupenglei on 17/7/11.
 */
public class Chapter10_4 {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("a", "5");
        properties.setProperty("b", "true");
        properties.setProperty("c", "-3");
    }

    int readDuration(Properties properties, String name) {
        String value = properties.getProperty(name);
        if (value != null) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    int readDuration2(Properties properties, String name) {
        return Optional.ofNullable(properties.getProperty(name))
                .flatMap(Chapter10_4::stringToInt)
                .filter(integer -> integer > 0)
                .orElse(0);

    }

    private static Optional<Integer> stringToInt(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
