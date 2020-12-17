package expression.exceptions;

import expression.*;

public class CheckedMultiply extends Multiply{
    public CheckedMultiply(MultiExpression left, MultiExpression right) {
        super(left, right);
    }
}
