package expression;

public class Count extends UnaryOperator {
    public Count(MultiExpression expr) {
        super(expr);
    }

    @Override
    public int evaluate(int x) {
        return Integer.bitCount(x);
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
