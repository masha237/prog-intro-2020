package expression;

public class Count extends BitUnaryOper {
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

    public double evaluate(double x) {
        return evaluate(x, 0);
    }

}
