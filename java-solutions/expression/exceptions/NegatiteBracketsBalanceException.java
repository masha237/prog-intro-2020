package expression.exceptions;

public class NegatiteBracketsBalanceException extends ParsingException {
    public NegatiteBracketsBalanceException(int ptr) {
        super("Negative balance at pos: " + ptr);
    }
}