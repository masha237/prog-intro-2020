package expression.exceptions;
import expression.*;
import java.util.*;

public class ExpressionParser implements Parser {
    private static final Set<String> TOKEN = Set.of("(", ")", "+", "-", "*", "/", "~", "^", "|", "&", "count", "x", "y", "z", "flip", "low", "minus");
    private static final Set<String> OPERATION = Set.of("(", "+", "-", "*", "/", "~", "^", "|", "&", "count", "flip", "low", "minus");
    private ArrayList<String> tokens;
    int ind = 0;

    @Override
    public TripleExpression parse(String expression) throws UnsupportedOperationException {
        ind = 0;
        tokens = getToken(expression);
        getMinus();
        return parseOr();
    }

    private void getMinus() {
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).equals("-")) {
                if (i == 0 || OPERATION.contains(tokens.get(i - 1))) {
                    tokens.set(i, "minus");
                }
            }
        }
    }

    private MultiExpression parseMaxPrior() {
        String s = next();

        switch (s) {
            case "~":
                return new Not(parseMaxPrior());
            case "count":
                return new Count(parseMaxPrior());
            case "flip":
                return new Flip(parseMaxPrior());
            case "low":
                return new Low(parseMaxPrior());
            case "minus":
                return new UnaryMinus(parseMaxPrior());
            case "(":
                MultiExpression res = parseOr();
                if (test(")")) {
                    return res;
                } else {
                    throw new UnsupportedOperationException("hasn't close bracket");
                }
            case "x":
            case "y":
            case "z":
                return new Variable(s);
            default:
                try {
                    return new Const(Integer.parseUnsignedInt(s));
                } catch (NumberFormatException e) {
                    throw new UnsupportedOperationException("is invalid expression");
                }
        }
    }

    private MultiExpression parseMulDiv() {
        MultiExpression left = parseMaxPrior();
        while (true) {
            if (test("*")) {
                left = new Multiply(left, parseMaxPrior());
            } else if (test("/")) {
                left = new Divide(left, parseMaxPrior());
            } else {
                return left;
            }
        }
    }

    private MultiExpression parseAddSub() {
        MultiExpression left = parseMulDiv();
        while (true) {
            if (test("+")) {
                left = new Add(left, parseMulDiv());
            } else if (test("-")) {
                left = new Subtract(left, parseMulDiv());
            } else {
                return left;
            }
        }
    }

    private MultiExpression parseAnd() {
        MultiExpression left = parseAddSub();
        while (true) {
            if (test("&")) {
                left = new And(left, parseAddSub());
            } else {
                return left;
            }
        }
    }

    private MultiExpression parseXor() {
        MultiExpression left = parseAnd();
        while (true) {
            if (test("^")) {
                left = new Xor(left, parseAnd());
            } else {
                return left;
            }
        }
    }

    private MultiExpression parseOr() {
        MultiExpression left = parseXor();
        while (true) {
            if (test("|")) {
                left = new Or(left, parseXor());
            } else {
                return left;
            }
        }
    }

    private boolean test(String s) {
        if (next().equals(s)) {
            return true;
        } else {
            ind--;
            return false;
        }
    }

    private String next() {
        if (ind != tokens.size()) {
            return tokens.get(ind++);
        } else {
            return "";
        }
    }

    private ArrayList<String> getToken(String expression) {
        ArrayList<String> res = new ArrayList<>();
        StringSource in = new StringSource(expression);
        StringBuilder sb = new StringBuilder();
        while (in.hasNext()) {
            sb.append(in.next());
            if (TOKEN.contains(sb.toString())) {
                res.add(sb.toString());
                sb = new StringBuilder();
            } else if (Character.isDigit(sb.toString().charAt(0))) {
                while (Character.isDigit(in.getNext())) {
                    sb.append(in.next());
                }
                res.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        if (sb.toString().length() != 0) {
            throw new UnsupportedOperationException(expression + " is invalid expression");
        }
        return res;
    }
}

