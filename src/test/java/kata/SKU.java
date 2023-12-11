package kata;

class SKU {
    private final String code;

    private SKU(String code) {
        this.code = code;
    }

    public static SKU of(String code) {
        return new SKU(code);
    }
}
