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
    protected int getPriority() {
        return 1;
    }

    @Override
    protected boolean getAssociativity() {
        return false;
    }

    @Override
    protected String getOperator() {
        return "/";
    }
}
