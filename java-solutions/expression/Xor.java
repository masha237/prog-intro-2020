package expression;

public class Xor extends BinaryOperator {
    public Xor(MultiExpression left, MultiExpression right) {
        super(left, right);
    }

    protected int evaluate(int x, int y) {
        return x ^ y;
    }

    protected double evaluate(double x, double y) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Xor hasn't double operation");
    }

    @Override
    protected int getRealPr() {
        return -2;
    }

    @Override
    protected int getPriorityR() {
        return -2;
    }

    @Override
    protected int getPriorityL() {
        return -2;
    }

    @Override
    protected char getOperator() {
        return '^';
    }
}
