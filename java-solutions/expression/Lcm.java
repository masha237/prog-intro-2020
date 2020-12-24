package expression;

public class Lcm extends BinaryOperator {
    public Lcm(MultiExpression left, MultiExpression right) {
        super(left, right);
    }

    protected int evaluate(int x, int y) {
        return x / gcd(x, y) * y;
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
        throw new UnsupportedOperationException("lcm(double)");
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
        return "lcm";
    }
}
