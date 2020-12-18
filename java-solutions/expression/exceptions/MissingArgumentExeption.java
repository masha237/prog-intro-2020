package expression.exceptions;

public class MissingArgumentExeption extends ParsingException {
    public MissingArgumentExeption(int ptr) {
        super("missing argument in index" + ptr);
    }
}
