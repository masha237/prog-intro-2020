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

    private boolean checkPriority(Expression a) {
        return ((a instanceof BinaryOperator) && ((BinaryOperator) a).getPriority() < this.getPriority());
    }

    private boolean associativeForRight(Expression a) {
        if (a instanceof BinaryOperator) {
            return (!((BinaryOperator) a).getAssociativity() || !this.getAssociativity()) && ((BinaryOperator) a).getPriority() <= this.getPriority();
        }
        return false;
    }

    @Override
    public String toMiniString() {
        StringBuilder sb = new StringBuilder();
        if (checkPriority(left)) {
            sb.append("(").append(left.toMiniString()).append(")");
        } else {
            sb.append(left.toMiniString());
        }
        sb.append(" ").append(getOperator()).append(" ");
        if (checkPriority(right) || associativeForRight(right)) {
            sb.append("(").append(right.toMiniString()).append(")");
        } else {
            sb.append(right.toMiniString());
        }
        return sb.toString();
    }

    protected abstract int getPriority();

    protected abstract boolean getAssociativity();

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
