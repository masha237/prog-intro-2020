package expression.parser;
import expression.*;
import expression.exceptions.MissingArgumentException;
import expression.exceptions.UnknownOperationException;

import java.util.*;

public class ExpressionParser implements Parser {
    private static final Set<String> TOKEN = Set.of("(", ")", "+", "-", "*", "/", "~", "^", "|", "&", "count", "x", "y", "z", "flip", "low", "sqrt", "abs", "gcd", "lcm");
    private static final Set<String> VARIABLES = Set.of("x", "y", "z");

    private boolean expectedUnaryOperation = true;
    private String nextTokens;
    private StringSource in;
    public static final Variable VX = new Variable("x");
    public static final Variable VY = new Variable("y");
    public static final Variable VZ = new Variable("z");

    @Override
    public TripleExpression parse(String expression) throws RuntimeException {
        in = new StringSource(expression);
        expectedUnaryOperation = true;
        nextTokens = nextToken();
        return parseOr();
    }

    private MultiExpression parseMaxPrior() {
        expectedUnaryOperation = true;
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
            case "-":
                // -123 -> - (123)
                return new UnaryMinus(parseMaxPrior());
            case "(":
                MultiExpression res = parseOr();
                if (test(")")) {
                    expectedUnaryOperation = false;
                    return res;
                } else {
                    throw new RuntimeException("hasn't close bracket");
                }
            case "sqrt":
                return new Sqrt(parseMaxPrior());
            case "abs":
                return new Abs(parseMaxPrior());
            case "x":
                return VX;
            case "y":
                return VY;
            case "z":
                return VZ;
            default:
                try {
                    // :NOTE: unsigned
                    return new Const(Integer.parseInt(s));
                } catch (NumberFormatException e) {
                    if (TOKEN.contains(s)) {
                        throw new MissingArgumentException("expected arument, found " + s);
                    }
                    throw new UnknownOperationException(s);
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
        if (nextTokens.equals(s)) {
            nextTokens = nextToken();
            return true;
        } else {
            return false;
        }
    }

    boolean isNumber(String s) {
        return s.length() != 0 && Character.isDigit(s.charAt(s.length() - 1));
    }

    private String next() {
        String t = nextTokens;
        if (VARIABLES.contains(t) || isNumber(t)) {
            expectedUnaryOperation = false;
        }
        if (hasNext()) {
            nextTokens = nextToken();
        } else {
            nextTokens = "";
        }
        return t;
    }

     public String nextToken() {
        StringBuilder sb = new StringBuilder();
        if (!hasNext()) {
            return "";
        }
        char ch = nextC();
        if (ch == '-' && expectedUnaryOperation && Character.isDigit(getNext())) {
            sb.append(ch);
            ch = nextC();
        }
        sb.append(ch);
        if (Character.isLetter(ch)) {
            while (Character.isLetter(getNext())) {
                sb.append(nextC());
            }
        } else if (Character.isDigit(ch)) {
            while (Character.isDigit(getNext())) {
                sb.append(nextC());
            }
        }
        return sb.toString();
    }

    public boolean hasNext() {
        skip();
        return in.hasNext();
    }

    public char getNext() {
        return in.getNext();
    }

    public char nextC() {
        return in.next();
    }

    private void skip() {
        while (Character.isWhitespace(in.getNext())) {
            in.next();
        }
    }
}

