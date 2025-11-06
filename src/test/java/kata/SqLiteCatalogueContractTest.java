package kata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.joda.money.Money;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;

class SqLiteCatalogueContractTest extends CatalogueContractTest {

    private final List<Connection> openConnections = new ArrayList<>();

    @AfterEach
    void closeConnections() throws SQLException {
        for (Connection connection : openConnections) {
            connection.close();
        }
        openConnections.clear();
    }

    @Override
    protected Catalogue createCatalogue(Map<Sku, Money> prices) {
        try {
            String dbId = UUID.randomUUID().toString();
            String jdbcUrl = "jdbc:sqlite:file:" + dbId + "?mode=memory&cache=shared";

            Connection connection = DriverManager.getConnection(jdbcUrl);
            openConnections.add(connection);

            Flyway.configure()
                    .dataSource(jdbcUrl, null, null)
                    .locations("classpath:db/migration")
                    .load()
                    .migrate();

            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("delete from Item");
            }

            try (PreparedStatement insert = connection.prepareStatement("insert into Item(SKU, PRICE) values (?, ?)")) {
                for (Map.Entry<Sku, Money> entry : prices.entrySet()) {
                    insert.setString(1, entry.getKey().asString());
                    insert.setInt(2, entry.getValue().getAmountMajorInt());
                    insert.addBatch();
                }
                insert.executeBatch();
            }

            return new SqLiteCatalogue(connection);
        } catch (SQLException exception) {
            throw new IllegalStateException("Failed to set up in-memory SQLite catalogue", exception);
        }
    }
}
