package kata;

import java.util.Map;

import org.joda.money.Money;

public final class Catalogue {

    private final Map<Sku, Money> prices;

    private Catalogue(Sku sku, Money money) {
        this.prices = Map.of(sku, money);
    }

    private Catalogue(Map<Sku, Money> sku) {
        this.prices = sku;
    }

    public static Catalogue of(Sku sku, Money money) {
        return new Catalogue(sku, money);
    }

    public static Catalogue of(Sku sku, Money money, Sku sku2, Money money2) {
        return new Catalogue(Map.of(sku, money, sku2, money2));
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
