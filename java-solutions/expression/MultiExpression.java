package expression;

public abstract class MultiExpression implements Expression, DoubleExpression, TripleExpression {
    public abstract int evaluate(int x, int y, int z);

    public abstract double evaluate(double x);

    public int evaluate(int x) {
        return evaluate(x, 0, 0);
    }

    public abstract String toMiniString();

    public abstract String toMiniString(int priority);
}
