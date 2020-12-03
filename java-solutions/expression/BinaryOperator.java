package expression;

import java.util.Objects;

public abstract class BinaryOperator extends MultiExpression {
    protected final MultiExpression left;
    protected final MultiExpression right;
    protected final int priorityL, priorityR, realPr;

    BinaryOperator(MultiExpression left, MultiExpression right, int priorityL, int priorityR, int realPr) {
        this.left = left;
        this.right = right;
        this.priorityL = priorityL;
        this.priorityR = priorityR;
        this.realPr = realPr;
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
        if (priority > realPr) {
            sb.append('(').append(left.toMiniString(priorityL));
        } else {
            sb.append(left.toMiniString(priorityL));
        }
        sb.append(" ").append(getOperator()).append(" ");
        if (priority > priorityR) {
            sb.append(right.toMiniString(priorityR));
        } else {
            sb.append(right.toMiniString(priorityR));
        }
        if (priority > realPr) {
            sb.append(')');
        }
        return sb.toString();
    }


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
