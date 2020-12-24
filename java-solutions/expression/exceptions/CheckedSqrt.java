package expression.exceptions;

import expression.*;

public class CheckedSqrt extends Sqrt {
    public CheckedSqrt(MultiExpression expr) {
        super(expr);
    }

    @Override
    public int evaluate(int x, int y, int z) throws ExpressionException {
        int l = expr.evaluate(x, y, z);
        if (l >= 0) {
            return (int)Math.sqrt(l);
        } else {
            throw new SqrtNegativeNumberException();
        }
    }
}
