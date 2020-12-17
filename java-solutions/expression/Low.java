package expression;

public class Low extends BitUnaryOper {
    public Low(MultiExpression expr) {
        super(expr);
    }

    @Override
    public int evaluate(int x) {
        return Integer.lowestOneBit(x);
    }

    @Override
    protected String getOperator() {
        return "low";
    }

    public double evaluate(double x) {
        return evaluate(x, 0);
    }
}
