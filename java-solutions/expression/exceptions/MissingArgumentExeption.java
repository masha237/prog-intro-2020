package expression.exceptions;

public class MissingArgumentExeption extends ParsingException {
    public MissingArgumentExeption(String s) {
        super("expected arument, found " + s);
    }
}
