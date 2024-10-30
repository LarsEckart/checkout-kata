package kata;

import java.util.ArrayList;
import java.util.List;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

class Checkout {

    private final List<Sku> cart = new ArrayList<>();
    public Catalogue catalogue;

    public Checkout(Catalogue catalogue) {
        this.catalogue = catalogue;
    }

    public void scan(Sku sku) {
        cart.add(sku);
    }

    public Money total() {
        Money total = Money.zero(CurrencyUnit.USD);
        for (Sku sku : cart) {
            total = total.plus(catalogue.getPriceFor(sku));
        }
        return total;
    }

}
