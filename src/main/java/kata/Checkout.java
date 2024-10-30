package kata;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

class Checkout {

    private final Cart cart;
    private final Catalogue catalogue;
    private final Discounts discounts;

    public Checkout(Catalogue catalogue, Discounts discounts) {
        this.cart = new Cart();
        this.catalogue = catalogue;
        this.discounts = discounts;
    }

    public void scan(Sku sku) {
        cart.addItem(sku);
    }

    public Money total() {
        Money total = Money.zero(CurrencyUnit.USD);
        for (Sku sku : cart) {
            total = total.plus(catalogue.getPriceFor(sku));
        }
        for (Discount discount : discounts) {
            total = total.minus(discount.calculateDiscount(cart));
        }
        return total;
    }

}
