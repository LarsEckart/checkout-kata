package kata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

class Checkout {

    private List<Sku> cart = new ArrayList<>();
    private Map<Sku, Money> prices = Map.of(Sku.of("A"), Money.of(CurrencyUnit.USD, 50));

    public void scan(Sku sku) {
        cart.add(sku);
    }

    public Money total() {
        Money total = Money.zero(CurrencyUnit.USD);
        for (Sku sku : cart) {
            total = total.plus(prices.get(sku));
        }
        return total;
    }
}
