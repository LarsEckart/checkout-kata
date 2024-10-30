package kata;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public final class Cart implements Iterable<Sku> {

    private final List<Sku> cart;

    public Cart() {
        this.cart = new ArrayList<>();
    }

    void addItem(Sku sku) {
        cart.add(sku);
    }

    boolean contains(Sku sku) {
        return cart.contains(sku);
    }

    Integer quantityOf(Sku sku1) {
        return counts().get(sku1);
    }

    private Map<Sku, Integer> counts() {
        return cart.stream()
                .collect(groupingBy(sku -> sku, summingInt(_ -> 1)));
    }

    @Override
    public String toString() {
        return "Cart[" +
                "cart1=" + cart + ']';
    }

    @Override
    public Iterator<Sku> iterator() {
        return cart.iterator();
    }

    @Override
    public void forEach(Consumer<? super Sku> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<Sku> spliterator() {
        return Iterable.super.spliterator();
    }
}
