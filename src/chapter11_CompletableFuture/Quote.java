package chapter11_CompletableFuture;

/**
 * Created by yupenglei on 17/7/12.
 */
class Quote {
    private final String mShopName;
    private final double mPrice;
    private final Discount.Code mDiscountCode;

    private Quote(String shopName, double price, Discount.Code discountCode) {
        mShopName = shopName;
        mPrice = price;
        mDiscountCode = discountCode;
    }

    static Quote parse(String s) {
        String[] split = s.split(":");
        String shopName = split[0];
        double price = Double.parseDouble(split[1]);
        Discount.Code code = Discount.Code.valueOf(split[2]);
        return new Quote(shopName, price, code);
    }

    String getShopName() {
        return mShopName;
    }

    double getPrice() {
        return mPrice;
    }

    Discount.Code getDiscountCode() {
        return mDiscountCode;
    }
}
