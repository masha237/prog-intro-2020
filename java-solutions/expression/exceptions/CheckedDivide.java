package expression.exceptions;

import expression.*;

public class CheckedDivide extends Divide {
    public CheckedDivide(MultiExpression left, MultiExpression right) {
        super(left, right);
    }
}
