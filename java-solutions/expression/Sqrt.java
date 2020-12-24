package expression;

public class Sqrt extends UnaryOperator {
    public Sqrt(MultiExpression expr) {
        super(expr);
    }

    @Override
    public int evaluate(int x) {
        return (int) Math.sqrt(x);
    }

    @Override
    protected String getOperator() {
        return "abs";
    }

    public double evaluate(double x) {
        return Math.sqrt(x);
    }
}
