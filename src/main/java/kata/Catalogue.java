package kata;

import org.joda.money.Money;

public interface Catalogue {

    Money getPriceFor(Sku sku);
}
