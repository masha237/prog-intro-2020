package expression.exceptions;

public class PositiveBracketsBalanceException extends ParsingException {
    public PositiveBracketsBalanceException(int ind) {
        super("Positive balance in expression: " + ind);
    }
}