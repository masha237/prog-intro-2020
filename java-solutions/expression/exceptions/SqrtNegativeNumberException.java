package expression.exceptions;

public class SqrtNegativeNumberException extends EvaluatingException {
    public SqrtNegativeNumberException() {
        super("sqrt(negative nubmer)");
    }
}

