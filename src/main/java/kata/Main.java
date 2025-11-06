package kata;

import java.sql.Connection;
import java.sql.DriverManager;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.flywaydb.core.Flyway;

class Main {

    private static final String SQLITE_URL = "jdbc:sqlite:file:checkout.db";
    private static final Sku SKU_A = Sku.of("A");
    private static final Sku SKU_B = Sku.of("B");

    public static void main(String[] args) throws Exception {
        withSqlite();
    }

    public static void withSqlite() throws Exception {
        migrateSchema();
        try (Connection connection = DriverManager.getConnection(SQLITE_URL)) {
            Checkout checkout = new Checkout(new SqLiteCatalogue(connection), defaultDiscounts());
            checkout.scan(SKU_A);
            System.out.println("Total: " + checkout.total());
        }
    }

    private static Discounts defaultDiscounts() {
        return Discounts.of(
                Discount.of(SKU_A, 3, Money.of(CurrencyUnit.USD, 20)),
                Discount.of(SKU_B, 2, Money.of(CurrencyUnit.USD, 15)));
    }

    private static void migrateSchema() {
        Flyway.configure()
                .dataSource(SQLITE_URL, null, null)
                .locations("classpath:db/migration")
                .load()
                .migrate();
    }
}
