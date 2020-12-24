package expression.exceptions;

public class MissingArgumentException extends ParsingException {
    public MissingArgumentException(String s) {
        super("expected argument, found " + s);
    }
}
