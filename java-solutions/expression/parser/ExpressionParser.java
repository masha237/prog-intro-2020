package expression.parser;
import expression.*;
import java.util.*;

public class ExpressionParser implements Parser {
    private static final Set<String> TOKEN = Set.of("(", ")", "+", "-", "*", "/", "~", "^", "|", "&", "count", "x", "y", "z", "flip", "low", "minus");
    private static final Set<String> OPERATION = Set.of("(", "+", "-", "*", "/", "~", "^", "|", "&", "count", "flip", "low", "minus");
    private ArrayList<String> tokens;
    private static MakerTokens makerToken = null;
    int ind = 0;

    @Override
    public TripleExpression parse(String expression) throws UnsupportedOperationException {
        ind = 0;
        makerToken = new MakerTokens(expression, ExpressionParser::charOfToken, TOKEN);
        tokens = makerToken.getAllToken();
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
        if (getNext().equals(s)) {
            next();
            return true;
        } else {
            return false;
        }
    }

    private String getNext() {
        if (ind != tokens.size()) {
            return tokens.get(ind);
        } else {
            return "";
        }
    }

    private String next() {
        if (ind != tokens.size()) {
            return tokens.get(ind++);
        } else {
            return "";
        }
    }
}

