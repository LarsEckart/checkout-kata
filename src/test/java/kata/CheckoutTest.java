package kata;

import org.joda.money.Money;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class CheckoutTest {

    @Test
    void product_a() {
        Checkout checkout = new Checkout();
        SKU sku = SKU.of("A");

        checkout.scan(sku);

        assertThat(checkout.total()).isEqualTo(Money.parse("USD 0.50"));
    }

    @Test
    void product_b() {
        Checkout checkout = new Checkout();
        SKU sku = SKU.of("B");

        checkout.scan(sku);

        assertThat(checkout.total()).isEqualTo(Money.parse("USD 0.30"));
    }

    @Test
    void product_c() {
        Checkout checkout = new Checkout();
        SKU sku = SKU.of("C");

        checkout.scan(sku);

        assertThat(checkout.total()).isEqualTo(Money.parse("USD 0.20"));
    }

    @Test
    void product_d() {
        Checkout checkout = new Checkout();
        SKU sku = SKU.of("D");

        checkout.scan(sku);

        assertThat(checkout.total()).isEqualTo(Money.parse("USD 0.15"));
    }

    @Test
    void product_a_3_times_then_discount() {
        Checkout checkout = new Checkout();
        SKU sku = SKU.of("A");

        checkout.scan(sku);
        checkout.scan(sku);
        checkout.scan(sku);

        assertThat(checkout.total()).isEqualTo(Money.parse("USD 1.30"));
    }
}
