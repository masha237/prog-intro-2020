package expression.exceptions;

public class DivNullExeption extends EvaluatingException {
    public DivNullExeption() {
        super("division by zero");
    }
}