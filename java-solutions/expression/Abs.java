package expression;

public class Abs extends UnaryOperator {
    public Abs(MultiExpression expr) {
        super(expr);
    }

    @Override
    public int evaluate(int x) {
        if (x < 0) {
            return -x;
        } else {
            return x;
        }
    }

    @Override
    protected String getOperator() {
        return "abs";
    }

    public double evaluate(double x) {
        if (x < 0) {
            return -x;
        } else {
            return x;
        }
    }
}
