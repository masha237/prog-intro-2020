package expression;

public class And extends BitBinaryOper {
    public And(MultiExpression left, MultiExpression right) {
        super(left, right);
    }

    protected int evaluate(int x, int y) {
        return x & y;
    }

    @Override
    protected int getPriority() {
        return -1;
    }

    @Override
    protected boolean getAssociativity() {
        return true;
    }

    @Override
    protected char getOperator() {
        return '&';
    }
}
