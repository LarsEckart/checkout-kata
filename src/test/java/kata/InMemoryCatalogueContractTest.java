package kata;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.joda.money.Money;

class InMemoryCatalogueContractTest extends CatalogueContractTest {

    @Override
    protected Catalogue createCatalogue(Map<Sku, Money> prices) {
        Iterator<Entry<Sku, Money>> iterator = prices.entrySet().iterator();
        if (!iterator.hasNext()) {
            throw new IllegalArgumentException("At least one price required");
        }

        Entry<Sku, Money> first = iterator.next();
        if (!iterator.hasNext()) {
            return InMemoryCatalogue.of(first.getKey(), first.getValue());
        }

        Entry<Sku, Money> second = iterator.next();
        if (iterator.hasNext()) {
            throw new IllegalArgumentException("Only up to two prices supported in tests");
        }

        return InMemoryCatalogue.of(first.getKey(), first.getValue(), second.getKey(), second.getValue());
    }
}

