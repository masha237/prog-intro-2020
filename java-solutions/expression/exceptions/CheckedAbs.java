package expression.exceptions;

import expression.*;

public class CheckedAbs extends Abs {
    public CheckedAbs(MultiExpression expr) {
        super(expr);
    }

    @Override
    public int evaluate(int x, int y, int z) throws ExpressionException {
        int l = expr.evaluate(x, y, z);
        return CheckedOperation.abs(l, "abs");
    }
}
