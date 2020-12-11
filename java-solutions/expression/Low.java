package expression;

public class Low extends UnaryOperator {
    public Low(MultiExpression expr) {
        super(expr);
    }

    @Override
    public int evaluate(int x) {
        return Integer.lowestOneBit(x);
    }

    @Override
    protected String getOperator() {
        return "count";
    }

    protected double evaluate(double x, double y) {
        throw new UnsupportedOperationException("Count hasn't double operation");
    }

    public double evaluate(double x) {
        return evaluate(x, 0);
    }
}
