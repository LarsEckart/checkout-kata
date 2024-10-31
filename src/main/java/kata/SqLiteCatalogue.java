package kata;

import java.sql.Connection;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import kata.jooq.tables.records.ItemRecord;

import static kata.jooq.tables.Item.ITEM;

class SqLiteCatalogue implements Catalogue {

    private final DSLContext dslContext;

    public SqLiteCatalogue(Connection connection) {
        this.dslContext = DSL.using(connection, SQLDialect.SQLITE);
    }

    @Override
    public Money getPriceFor(Sku sku) {
        Result<Record1<ItemRecord>> fetch = dslContext.select(ITEM)
                .from(ITEM)
                .where(ITEM.SKU.eq(sku.asString())).fetch();

        return fetch.isEmpty() ? null : Money.of(CurrencyUnit.USD, fetch.getFirst().value1().getPrice());
    }
}
