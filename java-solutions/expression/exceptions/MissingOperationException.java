package expression.exceptions;

public class MissingOperationException extends ParsingException {
    public MissingOperationException(int ptr) {
        super("missing operation in index" + ptr);
    }
}
