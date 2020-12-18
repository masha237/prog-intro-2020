package expression.parser;
import expression.*;
import java.util.*;

public class ExpressionParser implements Parser {
    private static final Set<String> TOKEN = Set.of("(", ")", "+", "-", "*", "/", "~", "^", "|", "&", "count", "x", "y", "z", "flip", "low");
    private String nextTokens;
    private static MakerTokens makerToken = null;

    @Override
    public TripleExpression parse(String expression) throws RuntimeException {
        makerToken = new MakerTokens(expression, ExpressionParser::charOfToken, TOKEN);
        nextTokens = makerToken.nextToken();
        return parseOr();
    }

    private static boolean charOfToken(char ch) {
        return !Character.isWhitespace(ch);
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
            case "-":
                // -123 -> - (123)
                return new UnaryMinus(parseMaxPrior());
            case "(":
                MultiExpression res = parseOr();
                if (test(")")) {
                    return res;
                } else {
                    throw new RuntimeException("hasn't close bracket");
                }
            case "x":
            case "y":
            case "z":
                // :NOTE: лишние переменные
                return new Variable(s);
            default:
                try {
                    // :NOTE: unsigned
                    return new Const(Integer.parseUnsignedInt(s));
                } catch (NumberFormatException e) {
                    throw new RuntimeException("is invalid expression");
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
            nextTokens = makerToken.nextToken();
            return true;
        } else {
            return false;
        }
    }

    private String next() {
        String t = nextTokens;
        if (makerToken.hasNext()) {
            nextTokens = makerToken.nextToken();
        } else {
            nextTokens = "";
        }
        return t;
    }
}

