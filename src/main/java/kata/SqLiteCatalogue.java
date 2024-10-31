package kata;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import static kata.jooq.tables.Item.ITEM;

class SqLiteCatalogue implements Catalogue {

    private final DSLContext dslContext;

    public SqLiteCatalogue(Connection connection) {
        this.dslContext = DSL.using(connection, SQLDialect.SQLITE);
    }

    @Override
    public Money getPriceFor(Sku sku) {
        Result<Record> result = dslContext
                .select()
                .from(ITEM)
                .where(ITEM.SKU.eq(sku.asString()))
                .fetch();

        return Money.of(CurrencyUnit.USD, result.getFirst().get(ITEM.PRICE));
    }
}
