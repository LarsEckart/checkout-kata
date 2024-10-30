package kata;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

class Checkout {

    private final Catalogue catalogue;
    private final Cart cart;

    public Checkout(Catalogue catalogue) {
        this.catalogue = catalogue;
        this.cart = new Cart();
    }

    public void scan(Sku sku) {
        cart.addItem(sku);
    }

    public Money total() {
        Money total = Money.zero(CurrencyUnit.USD);
        for (Sku sku : cart) {
            total = total.plus(catalogue.getPriceFor(sku));
        }
        total = total.minus(calculateDiscount(cart, Sku.of("A"), 3, Money.of(CurrencyUnit.USD, 20)));
        total = total.minus(calculateDiscount(cart, Sku.of("B"), 2, Money.of(CurrencyUnit.USD, 15)));

        return total;
    }

    private Money calculateDiscount(Cart cart, Sku sku, int quantity, Money discount) {
        if (cart.counts().containsKey(sku) && cart.counts().get(sku) >= quantity) {
            return discount;
        }
        return Money.zero(CurrencyUnit.USD);
    }

}
