package expression;

public class Variable extends MultiExpression {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    public int evaluate(int x, int y, int z) {
        if (name.equals("x")) {
            return x;
        } else if (name.equals("y")) {
            return y;
        } else {
            return z;
        }
    }

    public double evaluate(double x) {
        return x;
    }

    public String toString() {
        return name;
    }

    public String toMiniString() {
        return toMiniString(-1);
    }

    public String toMiniString(int x) {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variable a = (Variable) o;
        return name.equals(a.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
