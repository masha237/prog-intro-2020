package expression;

public class Divide extends BinaryOperator {
    public Divide(MultiExpression left, MultiExpression right) {
        super(left, right);
    }

    protected int evaluate(int x, int y) {
        return x / y;
    }

    protected double evaluate(double x, double y) {
        return x / y;
    }

    @Override
    protected int getRealPr() {
        return 2;
    }

    @Override
    protected int getPriorityR() {
        return 4;
    }

    @Override
    protected int getPriorityL() {
        return 2;
    }

    @Override
    protected char getOperator() {
        return '/';
    }
}
