package expression;

public abstract class BitUnaryOper extends UnaryOperator {
    public BitUnaryOper(MultiExpression expr) {
        super(expr);
    }

    protected double evaluate(double x, double y) {
        throw new UnsupportedOperationException("Count hasn't double operation");
    }

}
