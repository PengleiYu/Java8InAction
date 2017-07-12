package chapter11_CompletableFuture;

/**
 * Created by yupenglei on 17/7/12.
 */
class Discount {
    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMON(20);
        private final int mPercentage;

        Code(int percentage) {mPercentage = percentage;}
    }

    static String applyDiscount(Quote quote) {
        return String.format("%s prices is %.2f",
                quote.getShopName(), apply(quote.getPrice(), quote.getDiscountCode()));
    }

    private static double apply(double price, Code code) {
        Utils.randomDelay();
        return price * (100 - code.mPercentage) / 100;
    }
}
