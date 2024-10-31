package kata;

import java.sql.Connection;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.impl.DSL;

import kata.jooq.tables.Item;
import kata.jooq.tables.records.ItemRecord;

class SqLiteCatalogue implements Catalogue {

    private final DSLContext dslContext;

    public SqLiteCatalogue(Connection connection) {
        this.dslContext = DSL.using(connection);
    }

    @Override
    public Money getPriceFor(Sku sku) {
        Result<Record1<ItemRecord>> fetch = dslContext.select(Item.ITEM)
                .from(Item.ITEM)
                .where(Item.ITEM.SKU.eq(sku.asString())).fetch();

        return fetch.isEmpty() ? null : Money.of(CurrencyUnit.USD, fetch.getFirst().value1().getPrice());
    }
}
