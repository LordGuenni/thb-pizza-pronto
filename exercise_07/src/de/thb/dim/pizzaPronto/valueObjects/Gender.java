package de.thb.dim.pizzaPronto.valueObjects;

public enum Gender {
    M(1),
    F(2),
    D(3),
    U(4);

    private final int number;

    private Gender(int number) {
        this.number = number;
    }

    public int toNumber() {
        return number;
    }

    @Override
    public String toString() {
        switch (this) {
            case M:
                return "male";
            case F:
                return "female";
            case D:
                return "diverse";
            case U:
                return "unknown";
            default:
                return "";
        }
    }
}
