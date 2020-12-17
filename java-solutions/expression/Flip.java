package expression;

public class Flip extends BitUnaryOper {
    public Flip(MultiExpression expr) {
        super(expr);
    }

    @Override
    public int evaluate(int x) {
        int result = 0;
        while (x != 0) {
            result = (result << 1) + (x & 1);
            x >>>= 1;
        }
        return result;
    }

    @Override
    protected String getOperator() {
        return "flip";
    }

    public double evaluate(double x) {
        return evaluate(x, 0);
    }
}
