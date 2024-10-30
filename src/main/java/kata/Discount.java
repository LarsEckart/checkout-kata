package kata;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

public final class Discount {

    private final Sku sku;
    private final int quantity;
    private final Money discount;

    private Discount(Sku sku, int quantity, Money discount) {
        this.sku = sku;
        this.quantity = quantity;
        this.discount = discount;
    }

    public static Discount of(Sku sku, int quantity, Money discount) {
        return new Discount(sku, quantity, discount);
    }

    public Money calculateDiscount(Cart cart) {
        if (cart.counts().containsKey(sku)) {
            if (cart.counts().get(sku) >= quantity) {
                int times = cart.counts().get(sku) / quantity;
                return discount.multipliedBy(times);
            }
        }
        return Money.zero(CurrencyUnit.USD);
    }

    @Override
    public String toString() {
        return "Discount[" +
                "sku=" + sku + ", " +
                "quantity=" + quantity + ", " +
                "discount=" + discount + ']';
    }
}
