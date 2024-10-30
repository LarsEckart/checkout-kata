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
        return total;
    }

}
