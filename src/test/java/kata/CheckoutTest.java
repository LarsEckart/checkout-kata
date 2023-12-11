package kata;

import org.joda.money.Money;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class CheckoutTest {

    @Test
    void start_here() {
        Checkout checkout = new Checkout();
        SKU sku = SKU.of("A");

        checkout.scan(sku);

        assertThat(checkout.total()).isEqualTo(Money.parse("USD 0.50"));
    }
}
