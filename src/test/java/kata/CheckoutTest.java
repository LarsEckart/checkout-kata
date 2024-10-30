package kata;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CheckoutTest {

    @Test
    void buyOneAFor50() {
        Checkout checkout = new Checkout(Catalogue.of(Sku.of("A"), Money.of(CurrencyUnit.USD, 50)));
        checkout.scan(Sku.of("A"));

        assertThat(checkout.total()).isEqualTo(Money.of(CurrencyUnit.USD, 50));
    }

    @Test
    void buyTwoAFor100() {
        Checkout checkout = new Checkout(Catalogue.of(Sku.of("A"), Money.of(CurrencyUnit.USD, 50)));
        checkout.scan(Sku.of("A"));
        checkout.scan(Sku.of("A"));

        assertThat(checkout.total()).isEqualTo(Money.of(CurrencyUnit.USD, 100));
    }


}
