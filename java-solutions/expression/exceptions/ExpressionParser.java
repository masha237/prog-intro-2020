package expression.exceptions;
import expression.*;
import expression.parser.StringSource;

import java.util.*;

public class ExpressionParser implements Parser {
    private static final Set<String> TOKEN = Set.of("(", ")", "+", "-", "*", "/", "~", "^", "|", "&", "count", "x", "y", "z", "flip", "low", "minus");
    private static final Set<String> OPERATION = Set.of("(", "+", "-", "*", "/", "~", "^", "|", "&", "count", "flip", "low", "minus");

    private ArrayList<String> tokens;
    int ind = 0;

    @Override
    public TripleExpression parse(String expression) throws ExpressionException {
        ind = 0;
        tokens = getToken(expression);
        getMinus();
        check();
        System.out.println(expression);
        TripleExpression ans = parseOr();
        if (ind == tokens.size()) {
            return ans;
        } else {
            throw new MissingArgumentExeption(ind);
        }
    }

    private void check() throws ExpressionException {
        int bal = 0;
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).equals("(")) {
                bal++;
            } else if (tokens.get(i).equals(")")) {
                bal--;
                if (bal < 0) {
                    throw new NegatiteBracketsBalanceException(i);
                }
            }
        }
        if (bal != 0) {
            throw new PositiveBracketsBalanceException();
        }
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

    private MultiExpression parseMaxPrior() throws ParsingException {
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
                    return new Const(Integer.parseInt(s));
                } catch (NumberFormatException e) {
                    throw new MissingArgumentExeption(ind);
                }
        }
    }

    private MultiExpression parseMulDiv() throws ParsingException {
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

    private MultiExpression parseAddSub() throws ParsingException {
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

    private MultiExpression parseAnd() throws ParsingException {
        MultiExpression left = parseAddSub();
        while (true) {
            if (test("&")) {
                left = new And(left, parseAddSub());
            } else {
                return left;
            }
        }
    }

    private MultiExpression parseXor() throws ParsingException {
        MultiExpression left = parseAnd();
        while (true) {
            if (test("^")) {
                left = new Xor(left, parseAnd());
            } else {
                return left;
            }
        }
    }

    private MultiExpression parseOr() throws ParsingException {
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

    private void skipSpace(expression.parser.StringSource in) {
        while (Character.isWhitespace(in.getNext())) {
            in.next();
        }
    }

    private ArrayList<String> getToken(String expression) throws UnknownOperationException {
        ArrayList<String> res = new ArrayList<>();
        expression.parser.StringSource in = new StringSource(expression);
        StringBuilder sb = new StringBuilder();
        skipSpace(in);
        while (in.hasNext()) {
            sb.append(in.next());
            if (TOKEN.contains(sb.toString())) {
                res.add(sb.toString());
                sb.setLength(0);
            } else if (Character.isDigit(sb.charAt(0))) {
                while (Character.isDigit(in.getNext())) {
                    sb.append(in.next());
                }
                res.add(sb.toString());
                sb.setLength(0);
            }
            skipSpace(in);
        }
        if (sb.toString().length() != 0) {
            throw new UnknownOperationException(sb.toString());
        }
        return res;
    }
}

