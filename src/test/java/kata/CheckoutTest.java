package kata;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import javax.money.Monetary;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class CheckoutTest {

    @Test
    void start_here() {
        Money money = Money.ofMinor(Monetary.getCurrency("USD"), 50);

    }
}
