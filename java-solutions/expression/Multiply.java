package expression;

public class Multiply extends BinaryOperator {
    public Multiply(MultiExpression left, MultiExpression right) {
        super(left, right);
    }

    protected int evaluate(int x, int y) {
        return x * y;
    }

    protected double evaluate(double x, double y) {
        return x * y;
    }

    @Override
    protected int getPriority() {
        return 1;
    }

    @Override
    protected boolean getAssociativity() {
        return true;
    }

    @Override
    protected String getOperator() {
        return "*";
    }

}
