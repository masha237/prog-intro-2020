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
}
