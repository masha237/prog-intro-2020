package expression;

public class Or extends BinaryOperator {
    public Or(MultiExpression left, MultiExpression right) {
        super(left, right);
    }

    protected int evaluate(int x, int y) {
        return x | y;
    }

    protected double evaluate(double x, double y) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Or hasn't double operation");
    }

    // :NOTE: Упростить
    @Override
    protected int getRealPr() {
        return -3;
    }

    @Override
    protected int getPriorityR() {
        return -3;
    }

    @Override
    protected int getPriorityL() {
        return -3;
    }

    @Override
    protected char getOperator() {
        return '|';
    }
}
