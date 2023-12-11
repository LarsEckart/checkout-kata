package kata;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.util.ArrayList;
import java.util.List;

class Checkout {

    private Money total = Money.ofMajor(CurrencyUnit.USD, 0);
    private List<SKU> scannedItems = new ArrayList<>();

    public void scan(SKU sku) {
        scannedItems.add(sku);
    }

    public Money total() {
        for (SKU sku : scannedItems) {
            if (sku.equals(SKU.of("A"))) {
                total = total.plus(Money.ofMinor(CurrencyUnit.USD, 50));
            } else if (sku.equals(SKU.of("B"))) {
                total = total.plus(Money.ofMinor(CurrencyUnit.USD, 30));
            } else if (sku.equals(SKU.of("C"))) {
                total = total.plus(Money.ofMinor(CurrencyUnit.USD, 20));
            } else if (sku.equals(SKU.of("D"))) {
                total = total.plus(Money.ofMinor(CurrencyUnit.USD, 15));
            }
        }
        if (scannedItems.stream().filter(sku -> sku.equals(SKU.of("A"))).count() >= 3) {
            total = total.minus(Money.ofMinor(CurrencyUnit.USD, 20));
        }
        return total;
    }
}
