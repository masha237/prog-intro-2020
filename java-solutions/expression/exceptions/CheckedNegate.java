package expression.exceptions;

import expression.*;

public class CheckedNegate extends UnaryMinus {
    public CheckedNegate(MultiExpression expr) {
        super(expr);
    }
}
