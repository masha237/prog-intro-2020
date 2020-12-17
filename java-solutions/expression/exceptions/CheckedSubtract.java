package expression.exceptions;

import expression.*;

public class CheckedSubtract extends Subtract {
    public CheckedSubtract(MultiExpression left, MultiExpression right) {
        super(left, right);
    }
}
