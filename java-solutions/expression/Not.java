package expression;

public class Not extends UnaryOperator {
    public Not(MultiExpression expr) {
        super(expr);
    }

    @Override
    public int evaluate(int x) {
        return ~x;
    }

    @Override
    protected String getOperator() {
        return "~";
    }

    protected double evaluate(double x, double y) {
        throw new UnsupportedOperationException("Not hasn't double operation");
    }

    public double evaluate(double x) {
        return evaluate(x, 0);
    }
}
