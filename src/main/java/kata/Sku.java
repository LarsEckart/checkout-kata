package kata;

import java.util.Objects;

public final class Sku {

    private final String code;

    public Sku(String raw) {
        this.code = Objects.requireNonNull(raw);
    }

    public static Sku of(String raw) {
        return new Sku(raw);
    }

    @Override
    public boolean equals(Object o) {
        return o == this || (o instanceof Sku sku) && Objects.equals(code, sku.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "Sku{" + code + "}";
    }
}
