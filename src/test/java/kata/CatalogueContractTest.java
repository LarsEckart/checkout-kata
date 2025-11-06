package kata;

import java.util.Map;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
abstract class CatalogueContractTest {

    private static final Sku SKU_A = Sku.of("A");
    private static final Sku SKU_B = Sku.of("B");
    private static final Money PRICE_A = Money.of(CurrencyUnit.USD, 50);
    private static final Money PRICE_B = Money.of(CurrencyUnit.USD, 30);
    private static final Sku UNKNOWN_SKU = Sku.of("Z");

    protected abstract Catalogue createCatalogue(Map<Sku, Money> prices);

    @Test
    void returns_price_for_single_sku() {
        Catalogue catalogue = createCatalogue(Map.of(SKU_A, PRICE_A));

        assertThat(catalogue.getPriceFor(SKU_A)).isEqualTo(PRICE_A);
    }

    @Test
    void returns_price_for_second_sku() {
        Catalogue catalogue = createCatalogue(Map.of(SKU_A, PRICE_A, SKU_B, PRICE_B));

        assertThat(catalogue.getPriceFor(SKU_B)).isEqualTo(PRICE_B);
    }

    @Test
    void throws_when_price_missing() {
        Catalogue catalogue = createCatalogue(Map.of(SKU_A, PRICE_A));

        assertThatThrownBy(() -> catalogue.getPriceFor(UNKNOWN_SKU))
                .isInstanceOf(ItemNotFoundException.class)
                .hasMessageContaining("No price configured");
    }
}
