package kata;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CheckoutTest {

    @Test
    void buyingProductsThenTotalIsSumOfIndividualItems() {
        Checkout checkout = new Checkout(
                Catalogue.of(
                        Sku.of("A"), Money.of(CurrencyUnit.USD, 50),
                        Sku.of("B"), Money.of(CurrencyUnit.USD, 30)));

        checkout.scan(Sku.of("A"));
        checkout.scan(Sku.of("A"));
        checkout.scan(Sku.of("B"));

        assertThat(checkout.total()).isEqualTo(Money.of(CurrencyUnit.USD, 130));
    }

    @Test
    void buyingThreeAThenDiscountIsApplied() {
        Checkout checkout = new Checkout(
                Catalogue.of(
                        Sku.of("A"), Money.of(CurrencyUnit.USD, 50)));

        checkout.scan(Sku.of("A"));
        checkout.scan(Sku.of("A"));
        checkout.scan(Sku.of("A"));

        assertThat(checkout.total()).isEqualTo(Money.of(CurrencyUnit.USD, 130));
    }

    @Test
    void buyingSixAThenDiscountIsAppliedTwice() {
        Checkout checkout = new Checkout(
                Catalogue.of(
                        Sku.of("A"), Money.of(CurrencyUnit.USD, 50)));

        checkout.scan(Sku.of("A"));
        checkout.scan(Sku.of("A"));
        checkout.scan(Sku.of("A"));
        checkout.scan(Sku.of("A"));
        checkout.scan(Sku.of("A"));
        checkout.scan(Sku.of("A"));

        assertThat(checkout.total()).isEqualTo(Money.of(CurrencyUnit.USD, 260));
    }

    @Test
    void buyingTwoBThenDiscountIsApplied() {
        Checkout checkout = new Checkout(
                Catalogue.of(
                        Sku.of("B"), Money.of(CurrencyUnit.USD, 30)));

        checkout.scan(Sku.of("B"));
        checkout.scan(Sku.of("B"));
        checkout.scan(Sku.of("B"));

        assertThat(checkout.total()).isEqualTo(Money.of(CurrencyUnit.USD, 75));
    }
}
