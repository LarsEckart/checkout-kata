package kata;

import java.util.Objects;

class SKU {
    private final String code;

    private SKU(String code) {
        this.code = code;
    }

    public static SKU of(String code) {
        return new SKU(code);
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof SKU sku) && Objects.equals(code, sku.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
