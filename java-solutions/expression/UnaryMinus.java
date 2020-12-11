package expression;

public class UnaryMinus extends UnaryOperator {
    public UnaryMinus(MultiExpression expr) {
        super(expr);
    }

    @Override
    public int evaluate(int x) {
        return -x;
    }

    @Override
    protected String getOperator() {
        return "-";
    }

    public double evaluate(double x) {
        return -x;
    }
}
