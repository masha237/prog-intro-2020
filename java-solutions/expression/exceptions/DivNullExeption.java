package expression.exceptions;

public class DivNullExeption extends EvaluatingException {
    public DivNullExeption(String s) {
        super("division by zero" + s);
    }
}