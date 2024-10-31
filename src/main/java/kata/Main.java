package kata;

import java.sql.Connection;
import java.sql.DriverManager;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

class Main {

    public static void main(String[] args) throws Exception {
        Checkout checkout = new Checkout(
                InMemoryCatalogue.of(
                        Sku.of("A"), Money.of(CurrencyUnit.USD, 50),
                        Sku.of("B"), Money.of(CurrencyUnit.USD, 30)),
                Discounts.of(
                        Discount.of(Sku.of("A"), 3, Money.of(CurrencyUnit.USD, 20)),
                        Discount.of(Sku.of("B"), 2, Money.of(CurrencyUnit.USD, 15))));

        checkout.scan(Sku.of("A"));

        System.out.println(checkout.total());

        withSqlite();
    }

    public static void withSqlite() throws Exception {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:file:checkout.db")) {

            Catalogue catalogue = new SqLiteCatalogue(connection);

            Checkout checkout = new Checkout(
                    catalogue,
                    Discounts.of(
                            Discount.of(Sku.of("A"), 3, Money.of(CurrencyUnit.USD, 20)),
                            Discount.of(Sku.of("B"), 2, Money.of(CurrencyUnit.USD, 15))));

            checkout.scan(Sku.of("A"));

            System.out.println(checkout.total());
        }
    }
}
