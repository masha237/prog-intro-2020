package expression;

public class Add extends BinaryOperator {
    public Add(MultiExpression left, MultiExpression right) {
        super(left, right);
    }

    protected int evaluate(int x, int y) {
        return x + y;
    }

    protected double evaluate(double x, double y) {
        return x + y;
    }

    @Override
    protected int getRealPr() {
        return 0;
    }

    @Override
    protected int getPriorityR() {
        return 0;
    }

    @Override
    protected int getPriorityL() {
        return 0;
    }

    @Override
    protected char getOperator() {
        return '+';
    }
}
