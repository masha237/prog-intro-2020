package expression.exceptions;

import expression.*;

public class CheckedNegate extends UnaryMinus {
    public CheckedNegate(MultiExpression expr) {
        super(expr);
    }

    @Override
    public int evaluate(int x, int y, int z) throws ExpressionException {
        int l = expr.evaluate(x, y, z);
        return CheckedOperation.neg(l, "-");
    }
}
