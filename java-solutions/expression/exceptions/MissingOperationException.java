package expression.exceptions;

public class MissingOperationException extends ParsingException {
    public MissingOperationException(String s) {
        super("expected operation, found " + s);
    }
}
