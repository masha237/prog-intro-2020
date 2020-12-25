package expression.exceptions;
import expression.*;

import java.util.*;

public class ExpressionParser implements Parser {
    private static final Set<String> TOKEN = Set.of("(", ")", "+", "-", "*", "/", "~", "^", "|", "&", "count", "x", "y", "z", "flip", "low", "sqrt", "abs", "gcd", "lcm");
    private static final Set<String> VARIABLES = Set.of("x", "y", "z");

    private boolean expectedUnaryOperation = true;
    private String nextTokens;
    private StringSource in;
    private int ind;
    public static final Variable VX = new Variable("x");
    public static final Variable VY = new Variable("y");
    public static final Variable VZ = new Variable("z");

    @Override
    public TripleExpression parse(String expression) throws RuntimeException {
        in = new StringSource(expression);
        expectedUnaryOperation = true;
        nextTokens = nextToken();
        TripleExpression t = parseGcdLcm();
        if (nextTokens.length() > 0) {
            if (nextTokens.equals(")")) {
                throw new NegatiteBracketsBalanceException(getInd() + 1);
            } else if (TOKEN.contains(nextTokens) || Character.isDigit(nextTokens.charAt(nextTokens.length() - 1))) {
                throw new MissingOperationException(next() + ": " + (getInd() + 1));
            } else {
                throw new UnknownOperationException(next() + ": " + (getInd() + 1));
            }
        }
        return t;
    }

    private MultiExpression parseMaxPrior() {
        expectedUnaryOperation = true;
        String s = next();
        switch (s) {
            case "-":
                return new CheckedNegate(parseMaxPrior());
            case "(":
                MultiExpression res = parseGcdLcm();
                if (test(")")) {
                    expectedUnaryOperation = false;
                    return res;
                } else {
                    throw new PositiveBracketsBalanceException(getInd()); // ind in string
                }
            case "sqrt":
                return new CheckedSqrt(parseMaxPrior());
            case "abs":
                return new CheckedAbs(parseMaxPrior());
            case "x":
                return VX;
            case "y":
                return VY;
            case "z":
                return VZ;
            default:
                try {
                    return new Const(Integer.parseInt(s));
                } catch (NumberFormatException e) {
                    if (isNumber(s)) {
                        throw new OverflowException("constant " + s + ": " + (getInd() - s.length() + 1));
                    } else if (TOKEN.contains(s)) {
                        throw new MissingArgumentException(s + ": " + (getInd() - s.length() + 1));
                    } else if (s.equals(")")) {
                        throw new NegatiteBracketsBalanceException(getInd());
                    } else if (s.isEmpty()) {
                        throw new MissingArgumentException("empty string" + ": " + (getInd() + 1));
                    }
                    throw new UnknownOperationException(s + ": " + (getInd() - s.length() + 1));
                }
        }
    }

    private MultiExpression parseMulDiv() {
        MultiExpression left = parseMaxPrior();
        while (true) {
            if (test("*")) {
                left = new CheckedMultiply(left, parseMaxPrior());
            } else if (test("/")) {
                left = new CheckedDivide(left, parseMaxPrior());
            } else {
                return left;
            }
        }
    }

    private MultiExpression parseAddSub() {
        MultiExpression left = parseMulDiv();
        while (true) {
            if (test("+")) {
                left = new CheckedAdd(left, parseMulDiv());
            } else if (test("-")) {
                left = new CheckedSubtract(left, parseMulDiv());
            } else {
                return left;
            }
        }
    }

    private MultiExpression parseGcdLcm() {
        MultiExpression left = parseAddSub();
        while (true) {
            if (test("lcm")) {
                left = new CheckedLcm(left, parseAddSub());
            } else if (test("gcd")) {
                left = new CheckedGcd(left, parseAddSub());
            } else {
                return left;
            }
        }
    }

    private boolean test(String s) {
        if (nextTokens.equals(s)) {
            ind = getPos();
            nextTokens = nextToken();
            return true;
        } else {
            return false;
        }
    }

    boolean isNumber(String s) {
        return !s.isEmpty() && Character.isDigit(s.charAt(s.length() - 1));
    }

    private String next() {
        String t = nextTokens;
        ind = getPos();
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
            while (Character.isLetter(getNext()) || Character.isDigit(getNext())) {
                sb.append(nextC());
            }
        } else if (Character.isDigit(ch)) {
            while (Character.isDigit(getNext())) {
                sb.append(nextC());
            }
            return sb.toString();
        }
        if (TOKEN.contains(sb.toString())){
            return sb.toString();
        } else {
            throw new UnknownOperationException(sb.toString() + ": " + (getPos() - sb.length() + 1));
        }
    }

    public boolean hasNext() {
        skip();
        return in.hasNext();
    }

    public int getInd() {
        return ind;
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

    private int getPos() {
        return in.getPos();
    }
}

