package kata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

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
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
                Statement statement = connection.createStatement()) {
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            statement.executeUpdate("drop table if exists Items");
            statement.executeUpdate("create table Items (sku TEXT, price INTEGER)");
            statement.executeUpdate("insert into Items values( 'A', 50)");
            statement.executeUpdate("insert into Items values( 'B', 30)");
            statement.executeUpdate("insert into Items values( 'C', 20)");
            statement.executeUpdate("insert into Items values( 'D', 15)");

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
