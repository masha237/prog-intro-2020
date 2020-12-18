package expression.exceptions;

public class PositiveBracketsBalanceException extends ParsingException {
    public PositiveBracketsBalanceException() {
        super("Positive balance in expression");
    }
}