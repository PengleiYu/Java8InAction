package chapter11_CompletableFuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Stream;

/**
 * 尽快显示返回结果，而不是等到所有元素处理完毕
 * Created by yupenglei on 17/7/12.
 */
class Main3 {
    private static List<Shop> sShops = Utils.SHOPS;
    private static Executor sExecutor = Utils.EXECUTOR;

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        long start = System.nanoTime();
        CompletableFuture[] futures = findPricesStream("Iphone27s")
                .map(future -> future.thenAccept(
                        s -> System.out.println(s + "(done in "
                                + ((System.nanoTime() - start) / 1_000_000) + "mSecs)")
                ))
                .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(futures).join();
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");

    }

    /**
     * 为每个Future注册完成时回调
     */
    private static List<String> findPrices() {
        CompletableFuture[] futures = findPricesStream("IPhone27S")
                .map(future -> future.thenAccept(System.out::println))
                .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(futures).join();
//        CompletableFuture.anyOf(futures).join();
        System.out.println("Done!!!");
        return null;
    }

    /**
     * 返回Future流
     */
    private static Stream<CompletableFuture<String>> findPricesStream(String produce) {
        return sShops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> shop.getPrice(produce),
                        sExecutor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(
                        quote -> CompletableFuture.supplyAsync(
                                () -> Discount.applyDiscount(quote),
                                sExecutor)));
    }
}
