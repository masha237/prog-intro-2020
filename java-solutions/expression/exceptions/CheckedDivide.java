package expression.exceptions;

import expression.*;

public class CheckedDivide extends Divide {
    public CheckedDivide(MultiExpression left, MultiExpression right) {
        super(left, right);
    }

    @Override
    public int evaluate(int x, int y, int z) throws ExpressionException {
        int l = left.evaluate(x, y, z);
        int r = right.evaluate(x, y, z);

        if (r == 0) {
            throw new DivNullExeption();
        } else if (l == Integer.MIN_VALUE && r == -1) {
            throw new OverflowException();
        } else {
            return l / r;
        }
    }
}
