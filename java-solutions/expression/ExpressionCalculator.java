package expression;

public class ExpressionCalculator implements Expression, DoubleExpression, TripleExpression {
    private final MultiExpression expression;

    ExpressionCalculator(MultiExpression expression) {
        this.expression = expression;
    }

    public int evaluate(int x, int y, int z) {
        return expression.evaluate(x, y, z);
    }

    public int evaluate(int x) {
        return expression.evaluate(x, 0, 0);
    }

    public double evaluate(double x) {
        return expression.evaluate(x);
    }
}
