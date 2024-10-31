package kata;

import java.util.Map;

import org.joda.money.Money;

public final class InMemoryCatalogue implements Catalogue {

    private final Map<Sku, Money> prices;

    private InMemoryCatalogue(Sku sku, Money money) {
        this.prices = Map.of(sku, money);
    }

    private InMemoryCatalogue(Map<Sku, Money> sku) {
        this.prices = sku;
    }

    public static Catalogue of(Sku sku, Money money) {
        return new InMemoryCatalogue(sku, money);
    }

    public static Catalogue of(Sku sku, Money money, Sku sku2, Money money2) {
        return new InMemoryCatalogue(Map.of(sku, money, sku2, money2));
    }

    @Override
    public Money getPriceFor(Sku sku) {
        return prices.get(sku);
    }

    @Override
    public String toString() {
        return "Catalogue[" +
                "prices=" + prices + ']';
    }
}
