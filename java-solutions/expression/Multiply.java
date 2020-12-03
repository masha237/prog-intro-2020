package expression;

public class Multiply extends BinaryOperator {
    public Multiply(MultiExpression left, MultiExpression right) {
        super(left, right, 2, 3, 3);
    }

    protected int evaluate(int x, int y) {
        return x * y;
    }

    protected double evaluate(double x, double y) {
        return x * y;
    }

    @Override
    protected char getOperator() {
        return '*';
    }

}
