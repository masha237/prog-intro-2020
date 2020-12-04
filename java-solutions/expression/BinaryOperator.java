package expression;

import java.util.Objects;

public abstract class BinaryOperator extends MultiExpression {
    protected final MultiExpression left;
    protected final MultiExpression right;

    protected BinaryOperator(MultiExpression left, MultiExpression right) {
        this.left = left;
        this.right = right;
    }

    protected abstract int evaluate(int x, int y);

    protected abstract double evaluate(double x, double y);

    public int evaluate(int x, int y, int z) {
        int l = left.evaluate(x, y, z);
        int r = right.evaluate(x, y, z);
        return evaluate(l, r);
    }

    public double evaluate(double x) {
        double l = left.evaluate(x);
        double r = right.evaluate(x);
        return evaluate(l, r);
    }

    public String toString() {
        return "(" + left + " " + getOperator() + " " + right + ")";
    }

    public String toMiniString() {
        return toMiniString(-1);
    }

    public String toMiniString(int priority) {
        StringBuilder sb = new StringBuilder();
        if (priority > getRealPr()) {
            sb.append('(').append(left.toMiniString(getPriorityL()));
        } else {
            sb.append(left.toMiniString(getPriorityL()));
        }
        sb.append(" ").append(getOperator()).append(" ");
        if (priority > getPriorityR()) {
            sb.append(right.toMiniString(getPriorityR()));
        } else {
            sb.append(right.toMiniString(getPriorityR()));
        }
        if (priority > getRealPr()) {
            sb.append(')');
        }
        return sb.toString();
    }

    protected abstract int getRealPr();

    protected abstract int getPriorityR();

    protected abstract int getPriorityL();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinaryOperator that = (BinaryOperator) o;
        return Objects.equals(left, that.left) &&
                Objects.equals(right, that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right, getOperator());
    }

    abstract protected char getOperator();
}
