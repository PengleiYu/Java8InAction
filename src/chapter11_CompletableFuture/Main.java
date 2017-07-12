package chapter11_CompletableFuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

/**
 * CompletableFuture加ThreadPool方式可避免线程的io等待，适合非计算密集型线程
 * Created by yupenglei on 17/7/12.
 */
class Main {
    private static final List<Shop> SHOPS = Utils.SHOPS;
    private static final Executor EXECUTOR = Utils.EXECUTOR;

    public static void main(String[] args) {
        Utils.test(Main::findPricesFutureThreadPool);
        Utils.test(Main::findPricesFuture);
        Utils.test(Main::findPricesAsync);
        Utils.test(Main::findPricesSync);
    }


    /**
     * 4shops,未大于CPU数
     * 对指定商品从所有商店获取价格
     * 最直接的实现
     * 串行耗时4129ms
     */
    private static List<String> findPricesSync(String product) {
        return SHOPS
                .stream()
                .map(shop -> shop.getPrice(product))
                .collect(Collectors.toList());
    }

    /**
     * 4shops,未大于CPU数
     * 并行耗时1154ms
     */
    private static List<String> findPricesAsync(String product) {
        return SHOPS
                .parallelStream()
                .map(shop -> shop.getPrice(product))
                .collect(Collectors.toList());
    }

    /**
     * 4shops,未大于CPU数
     * 使用CompletableFuture实现
     * 耗时4150ms
     * 若使用搜集再流化方式则耗时2135ms,比并行多出来1000ms
     * 注意两种方式的不同
     */
    private static List<String> findPricesFuture(String product) {
        return SHOPS.stream()
                .map(shop ->
                        CompletableFuture.supplyAsync(() -> shop.getPrice(product))
                )
                .collect(Collectors.toList())
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    /**
     * 使用了ThreadPool的CompletableFuture
     * 无论多少shops，耗时均为1000ms左右
     */
    private static List<String> findPricesFutureThreadPool(String product) {
        return SHOPS.stream()
                .map(shop ->
                        CompletableFuture.supplyAsync(() -> shop.getPrice(product), EXECUTOR))
                .collect(Collectors.toList())
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }


}
