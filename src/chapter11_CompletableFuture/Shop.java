package chapter11_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;


/**
 * Created by yupenglei on 17/7/12.
 */
class Shop {
    private String mName;

    Shop(String name) {
        mName = name;
    }

    /**
     * 根据产品获取价格
     */
    String getPrice(String product) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[
                Utils.sRandom.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", mName, price, code);
    }

    /**
     * 异步获取产品价格
     */
    private Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() ->
        {
            try {
                futurePrice.complete(calculatePrice(product));
            } catch (Exception e) {
                futurePrice.completeExceptionally(e);//通知调用线程
            }
        }).start();
        return futurePrice;
    }

    /**
     * 使用工厂方法创建CompletableFuture，内部已提供错误管理机制
     */
    private Future<Double> getPriceAsync2(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    /**
     * 根据产品计算价格
     */
    private double calculatePrice(String product) {
        Utils.randomDelay();
        return Utils.sRandom.nextDouble() * product.charAt(0) + product.charAt(1);
    }

}
