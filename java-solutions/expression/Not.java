package expression;

public class Not extends BitUnaryOper {
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

    public double evaluate(double x) {
        return evaluate(x, 0);
    }
}
