package expression.exceptions;

import expression.*;

public class CheckedAdd extends Add {
    public CheckedAdd(MultiExpression left, MultiExpression right) {
        super(left, right);
    }
}
