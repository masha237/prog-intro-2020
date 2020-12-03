package expression;

public class Add extends BinaryOperator {
    public Add(MultiExpression left, MultiExpression right) {
        super(left, right, 0, 0, 0);
    }

    protected int evaluate(int x, int y) {
        return x + y;
    }

    protected double evaluate(double x, double y) {
        return x + y;
    }

    @Override
    protected char getOperator() {
        return '+';
    }
}
