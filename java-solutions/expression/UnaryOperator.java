package expression;

import java.util.Objects;

public abstract class UnaryOperator extends MultiExpression {
    protected final MultiExpression expr;

    protected UnaryOperator(MultiExpression expr) {
        this.expr = expr;
    }

    public int evaluate(int x, int y, int z) {
        return evaluate(expr.evaluate(x, y, z));
    }

    public abstract double evaluate(double x);

    public String toString() {
        return getOperator() + expr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnaryOperator that = (UnaryOperator) o;
        return Objects.equals(expr, that.expr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expr, getOperator());
    }

    @Override
    public String toMiniString() {
        return getOperator() + expr.toMiniString();
    }

    abstract protected String getOperator();
}
