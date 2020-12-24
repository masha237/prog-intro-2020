package expression;

public class Gcd extends BinaryOperator {
    public Gcd(MultiExpression left, MultiExpression right) {
        super(left, right);
    }

    protected int evaluate(int x, int y) {
        return gcd(x, y);
    }

    private int gcd(int x, int y) {
        while (y != 0) {
            int t = x % y;
            x = y;
            y = t;
        }
        return x;
    }

    protected double evaluate(double x, double y) {
        throw new UnsupportedOperationException("gcd(double)");
    }

    @Override
    protected int getPriority() {
        return -4;
    }

    @Override
    protected boolean getAssociativity() {
        return true;
    }

    @Override
    protected String getOperator() {
        return "gcd";
    }
}
