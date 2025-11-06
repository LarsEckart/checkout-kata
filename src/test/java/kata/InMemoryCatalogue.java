package kata;

import java.util.Map;

import org.joda.money.Money;

public final class InMemoryCatalogue implements Catalogue {

    private final Map<Sku, Money> prices;

    private InMemoryCatalogue(Map<Sku, Money> sku) {
        this.prices = sku;
    }

    private InMemoryCatalogue(Sku sku, Money money) {
        this(Map.of(sku, money));
    }

    public static Catalogue of(Sku sku, Money money) {
        return new InMemoryCatalogue(sku, money);
    }

    public static Catalogue of(Sku sku, Money money, Sku sku2, Money money2) {
        return new InMemoryCatalogue(Map.of(sku, money, sku2, money2));
    }

    @Override
    public Money getPriceFor(Sku sku) {
        Money price = prices.get(sku);
        if (price != null) {
            return price;
        }
        throw new ItemNotFoundException("No price configured for " + sku);
    }

    @Override
    public String toString() {
        return "Catalogue[" +
                "prices=" + prices + ']';
    }
}
