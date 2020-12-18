package expression;

import expression.exceptions.ExpressionException;

public abstract class MultiExpression implements Expression, DoubleExpression, TripleExpression {
    public abstract int evaluate(int x, int y, int z) throws ExpressionException;

    public abstract double evaluate(double x);

    public int evaluate(int x) throws ExpressionException {
        return evaluate(x, 0, 0);
    }

    public abstract String toMiniString();
}
