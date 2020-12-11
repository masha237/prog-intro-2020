package expression;

public class Flip extends UnaryOperator {
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

    protected double evaluate(double x, double y) {
        throw new UnsupportedOperationException("Count hasn't double operation");
    }

    public double evaluate(double x) {
        return evaluate(x, 0);
    }
}
