package chapter11_CompletableFuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

/**
 * 每个元素都要执行两个顺序执行的耗时任务
 * Created by yupenglei on 17/7/12.
 */
class Main2 {
    private static List<Shop> sShops = Utils.SHOPS;
    private static Executor sExecutor = Utils.EXECUTOR;

    public static void main(String[] args) {
        Utils.test(Main2::findPricesFutureThreadPool);
        Utils.test(Main2::findPrices);
    }


    /**
     * 一个任务完成再去执行另一个
     */
    private static List<String> findPricesFutureThreadPool(String product) {
        return sShops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> shop.getPrice(product),
                        sExecutor))
                .map(stringCompletableFuture -> stringCompletableFuture.thenApply(Quote::parse))
                .map(quoteCompletableFuture -> quoteCompletableFuture.thenCompose(
                        quote -> CompletableFuture.supplyAsync(
                                () -> Discount.applyDiscount(quote),
                                sExecutor)
                ))
                .collect(Collectors.toList())
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    /**
     * 使用普通的流
     */
    private static List<String> findPrices(String product) {
        return sShops
//                .stream()
                .parallelStream()
                .map(shop -> shop.getPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }
}
