package expression;

public class Divide extends BinaryOperator {
    public Divide(MultiExpression left, MultiExpression right) {
        super(left, right, 2, 4, 2);
    }

    protected int evaluate(int x, int y) {
        return x / y;
    }

    protected double evaluate(double x, double y) {
        return x / y;
    }

    @Override
    protected char getOperator() {
        return '/';
    }
}
