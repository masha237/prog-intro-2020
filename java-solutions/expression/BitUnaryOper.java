package expression;

public abstract class BitUnaryOper extends UnaryOperator {
    public BitUnaryOper(MultiExpression expr) {
        super(expr);
    }

    @Override
    public double evaluate(double x) {
        throw new UnsupportedOperationException("Count hasn't double operation");
    }

}
