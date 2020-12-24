package expression.exceptions;

import expression.*;

public class CheckedLcm extends Gcd {
    public CheckedLcm(MultiExpression left, MultiExpression right) {
        super(left, right);
    }

    @Override
    public int evaluate(int x, int y, int z) throws ExpressionException {
        int l = left.evaluate(x, y, z);
        int r = right.evaluate(x, y, z);

        int gcd = CheckedOperation.gcd(l, r, "lcm");
        if (gcd == 0) {
            return 0;
        } else {

            return CheckedOperation.mul(CheckedOperation.div(l, gcd, "lcm"), r, "lcm");
        }
    }
}
