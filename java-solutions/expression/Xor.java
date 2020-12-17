package expression;

public class Xor extends BitBinaryOper {
    public Xor(MultiExpression left, MultiExpression right) {
        super(left, right);
    }

    protected int evaluate(int x, int y) {
        return x ^ y;
    }

    @Override
    protected int getPriority() {
        return -2;
    }

    @Override
    protected boolean getAssociativity() {
        return true;
    }

    @Override
    protected char getOperator() {
        return '^';
    }

}
