package expression;

public class And extends BinaryOperator {
    public And(MultiExpression left, MultiExpression right) {
        super(left, right);
    }

    protected int evaluate(int x, int y) {
        return x & y;
    }

    protected double evaluate(double x, double y) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("And hasn't double operation");
    }

    @Override
    protected int getRealPr() {
        return -1;
    }

    @Override
    protected int getPriorityR() {
        return -1;
    }

    @Override
    protected int getPriorityL() {
        return -1;
    }

    @Override
    protected char getOperator() {
        return '&';
    }
}
