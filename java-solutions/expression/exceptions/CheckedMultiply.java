package expression.exceptions;

import expression.*;

public class CheckedMultiply extends Multiply{
    public CheckedMultiply(MultiExpression left, MultiExpression right) {
        super(left, right);
    }

    @Override
    public int evaluate(int x, int y, int z) throws ExpressionException {
        int l = left.evaluate(x, y, z);
        int r = right.evaluate(x, y, z);

        if (l == 0 || r == 0) {
            return 0;
        }

        if (l > 0 && r > 0) {
            if (Integer.MAX_VALUE / l >= r) {
                return l * r;
            } else {
                throw new OverflowException();
            }
        } else if (l < 0 && r < 0) {
            if (Integer.MAX_VALUE / l <= r) {
                return l * r;
            } else {
                throw new OverflowException();
            }
        } else if (l > 0) {
            if (Integer.MIN_VALUE / l <= r) {
                return l * r;
            } else {
                throw new OverflowException();
            }
        } else {
            if (Integer.MIN_VALUE / r <= l) {
                return l * r;
            } else {
                throw new OverflowException();
            }
        }
    }
}
