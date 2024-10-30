package kata;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

class Checkout {

    public void scan(Sku sku) {

    }

    public Money total() {
        return Money.of(CurrencyUnit.USD, 50);
    }
}
