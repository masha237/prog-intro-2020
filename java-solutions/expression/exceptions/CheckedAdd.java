package expression.exceptions;

import expression.*;

public class CheckedAdd extends Add {
    public CheckedAdd(MultiExpression left, MultiExpression right) {
        super(left, right);
    }

    @Override
    public int evaluate(int x, int y, int z) throws ExpressionException {
        int l = left.evaluate(x, y, z);
        int r = right.evaluate(x, y, z);
        return CheckedOperation.add(l, r, "+");
    }
}
