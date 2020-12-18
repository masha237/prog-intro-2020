package expression.exceptions;

public class UnknownOperationException extends ParsingException{
    public UnknownOperationException(String s) {
        super("Unknown operation " + s);
    }
}
