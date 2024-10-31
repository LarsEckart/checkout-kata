package kata;

import java.sql.Connection;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.impl.DSL;

import kata.jooq.tables.Items;
import kata.jooq.tables.records.ItemsRecord;

class SqLiteCatalogue implements Catalogue {

    private final DSLContext dslContext;

    public SqLiteCatalogue(Connection connection) {
        this.dslContext = DSL.using(connection);
    }

    @Override
    public Money getPriceFor(Sku sku) {
        Result<Record1<ItemsRecord>> fetch = dslContext.select(Items.ITEMS)
                .from(Items.ITEMS)
                .where(Items.ITEMS.SKU.eq(sku.asString())).fetch();

        return fetch.isEmpty() ? null : Money.of(CurrencyUnit.USD, fetch.getFirst().value1().getPrice());
    }
}
