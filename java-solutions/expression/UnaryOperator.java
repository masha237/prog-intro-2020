package expression;

import java.util.Objects;

public abstract class UnaryOperator extends MultiExpression {
    protected final MultiExpression expr;
    protected final int priority;

    protected UnaryOperator(MultiExpression expr) {
        this.expr = expr;
        this.priority = Integer.MAX_VALUE;
    }

    public int evaluate(int x, int y, int z) {
        return evaluate(expr.evaluate(x, y, z));
    }

    public abstract double evaluate(double x);

    public String toString() {
        return getOperator() + expr;
    }

    public String toMiniString() {
        return toMiniString(-1);
    }

    public String toMiniString(int priority) {
        StringBuilder sb = new StringBuilder();
        if (priority > this.priority) {
            sb.append('(').append(expr.toMiniString(this.priority)).append(" ").append(getOperator()).append(')');
        } else {
            sb.append(expr.toMiniString(this.priority));
        }
        return sb.toString();
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

    abstract protected String getOperator();

    protected int getRealPr() {
        return priority;
    }
}
