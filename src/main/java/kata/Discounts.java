package kata;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public final class Discounts implements Iterable<Discount> {

    private final List<Discount> discounts;

    private Discounts(Discount[] discounts) {
        this.discounts = List.of(discounts);
    }

    public static Discounts of(Discount... discounts) {
        return new Discounts(discounts);
    }

    public static Discounts none() {
        return new Discounts(new Discount[0]);
    }

    @Override
    public Iterator<Discount> iterator() {
        return discounts.iterator();
    }

    @Override
    public void forEach(Consumer<? super Discount> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<Discount> spliterator() {
        return Iterable.super.spliterator();
    }

}
