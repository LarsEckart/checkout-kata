package kata;


import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

class Checkout {

    private Money total = Money.ofMajor(CurrencyUnit.USD, 0);


    public void scan(SKU sku) {
        total = total.plus(Money.ofMinor(CurrencyUnit.USD, 50));
    }

    public Money total() {
        return total;
    }
}
