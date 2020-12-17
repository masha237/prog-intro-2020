package expression.exceptions;

public class BracketsBalanceException extends ParsingException {
    public BracketsBalanceException(int ptr) {
        super("Negative balance at pos: " + ptr);
    }
}