package expression.exceptions;

import expression.*;

public class CheckedGcd extends Gcd {
    public CheckedGcd(MultiExpression left, MultiExpression right) {
        super(left, right);
    }

    @Override
    public int evaluate(int x, int y, int z) throws ExpressionException {
        int l = left.evaluate(x, y, z);
        int r = right.evaluate(x, y, z);
        return CheckedOperation.gcd(l, r, "gcd");
    }
}
