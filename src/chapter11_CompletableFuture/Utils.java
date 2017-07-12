package chapter11_CompletableFuture;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Function;

/**
 * Created by yupenglei on 17/7/12.
 */
class Utils {
    static Random sRandom = new Random();
    static final List<Shop> SHOPS = Arrays.asList(
            new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"),
            new Shop("TheFiveShop"),
            new Shop("TheSixShop"),
            new Shop("TheSevenShop"),
            new Shop("TheEightShop"),
            new Shop("TheNineShop")
    );
    static final Executor EXECUTOR = Executors.newFixedThreadPool(Math.min(SHOPS.size(), 100),
            r -> {
                Thread thread = new Thread(r);
                thread.setDaemon(true);
                return thread;
            }
    );

    static void test(Function<String, List<String>> function) {
        long start = System.nanoTime();
        System.out.println(function.apply("IPhone278S"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

    /**
     * 模拟固定延迟
     */
    static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 模拟随机延迟
     */
    static void randomDelay() {
        int delay = 500 + sRandom.nextInt(2000);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
