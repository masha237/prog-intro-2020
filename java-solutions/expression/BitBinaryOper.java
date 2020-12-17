package expression;

public abstract class BitBinaryOper extends BinaryOperator {
    protected BitBinaryOper(MultiExpression left, MultiExpression right) {
        super(left, right);
    }

    protected double evaluate(double x, double y) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(getOperator() + " hasn't double operation");
    }
}
