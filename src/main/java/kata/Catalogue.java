package kata;

import java.util.Map;

import org.joda.money.Money;

public final class Catalogue {

    private final Map<Sku, Money> prices;

    private Catalogue(Sku sku, Money money) {
        this.prices = Map.of(sku, money);
    }

    public static Catalogue of(Sku sku, Money money) {
        return new Catalogue(sku, money);
    }

    public Money getPriceFor(Sku sku) {
        return prices.get(sku);
    }

    @Override
    public String toString() {
        return "Catalogue[" +
                "prices=" + prices + ']';
    }
}
