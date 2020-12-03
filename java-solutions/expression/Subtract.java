package expression;

public class Subtract extends BinaryOperator {
    public Subtract(MultiExpression left, MultiExpression right) {
        super(left, right, 0, 1, 0);
    }

    protected int evaluate(int x, int y) {
        return x - y;
    }

    protected double evaluate(double x, double y) {
        return x - y;
    }

    @Override
    protected char getOperator() {
        return '-';
    }
}
