package expression;

import java.util.Objects;

public class Const extends MultiExpression {
    private final Number value;

    public Const(double value) {
        this.value = value;
    }

    public Const(int value) {
        this.value = value;
    }


    public int evaluate(int x, int y, int z) {
        return value.intValue();
    }

    public double evaluate(double x) {
        return value.doubleValue();
    }

    public String toString() {
        return String.valueOf(value);
    }

    public String toMiniString() {
        return toMiniString(-1);
    }

    public String toMiniString(int x) {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Const aConst = (Const) o;
        return value.equals(aConst.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
