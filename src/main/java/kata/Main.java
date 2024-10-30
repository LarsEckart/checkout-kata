package kata;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

class Main {

    public static void main(String[] args) {
        Checkout checkout = new Checkout(
                Catalogue.of(
                        Sku.of("A"), Money.of(CurrencyUnit.USD, 50),
                        Sku.of("B"), Money.of(CurrencyUnit.USD, 30)), Discounts.of(
                Discount.of(Sku.of("A"), 3, Money.of(CurrencyUnit.USD, 20)),
                Discount.of(Sku.of("B"), 2, Money.of(CurrencyUnit.USD, 15))));

        checkout.scan(Sku.of("A"));

        System.out.println(checkout.total());
    }

}
