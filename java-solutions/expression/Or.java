package expression;

public class Or extends BitBinaryOper {
    public Or(MultiExpression left, MultiExpression right) {
        super(left, right);
    }

    protected int evaluate(int x, int y) {
        return x | y;
    }

    // :NOTE: Упростить
    @Override
    protected int getPriority() {
        return -3;
    }

    @Override
    protected boolean getAssociativity() {
        return true;
    }

    @Override
    protected String getOperator() {
        return "|";
    }
}
