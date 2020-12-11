package expression.parser;
import expression.*;
import java.util.*;

public class ExpressionParser implements Parser {
    // :NOTE: Map?
    // :NOTE: Константы
    private final int[] prior = new int[Character.MAX_VALUE];
    private final Set<String> unaryOper = Set.of(
            "-",
            "~",
            "count",
            "flip",
            "low"
    );

    boolean isOperation(char ch) {
        return prior[ch] != Integer.MIN_VALUE;
    }

    @Override
    public TripleExpression parse(String expression) throws UnsupportedOperationException {
        Arrays.fill(prior, Integer.MIN_VALUE);
        prior['^'] = -1;
        prior['|'] = -2;
        prior['&'] = 0;
        prior['-'] = prior['+'] = 1;
        prior['*'] = prior['/'] = 2;
        prior['m'] = prior['n'] = prior['c'] = prior['f'] = prior['l'] = Integer.MAX_VALUE;

        // :NOTE: Stack -> ArrayDeque
        Stack<Character> oper = new Stack<>();
        Stack<MultiExpression> num = new Stack<>();
        int ind = 0;
        boolean fl = true;
        while (ind < expression.length()) {
            if (expression.charAt(ind) == ')') {
                fl = false;
                while (oper.peek() != '(') {
                    union(oper, num);
                }
                oper.pop();
            } else if (expression.charAt(ind) == '(') {
                fl = true;
                oper.push('(');
            } else if (!fl && isOperation(expression.charAt(ind)) && !num.empty()) {
                int prior = this.prior[expression.charAt(ind)];
                while (!oper.empty() && isOperation(oper.peek()) && prior <= this.prior[oper.peek()]) {
                    union(oper, num);
                }
                fl = true;
                oper.push(expression.charAt(ind));
            } else if (Character.isDigit(expression.charAt(ind))) {
                fl = false;
                int cur = 0;
                while (ind != expression.length() && Character.isDigit(expression.charAt(ind))) {
                    // :NOTE: парсинг "руками"
                    cur = 10 * cur + expression.charAt(ind) - '0';
                    ind++;
                }
                ind--;
                num.push(new Const(cur));
                while (!oper.empty() && this.prior[oper.peek()] == Integer.MAX_VALUE) {
                    union(oper, num);
                }
                // :NOTE: Обращение по индексам
            } else if (expression.charAt(ind) == 'z' || expression.charAt(ind) == 'y' || expression.charAt(ind) == 'x') {
                fl = false;
                num.push(new Variable(String.valueOf(expression.charAt(ind))));
                while (!oper.empty() && this.prior[oper.peek()] == Integer.MAX_VALUE) {
                    union(oper, num);
                }
            } else if (!Character.isWhitespace(expression.charAt(ind))) {
                fl = true;
                StringBuilder sb = new StringBuilder();
                while (ind != expression.length()) {
                    sb.append(expression.charAt(ind));
                    ind++;
                    if (unaryOper.contains(sb.toString()))
                        break;
                }
                ind--;
                if (!unaryOper.contains(sb.toString()))
                    throw new UnsupportedOperationException(sb.toString() + " is invalid operatin");
                switch (sb.toString()) {
                    case "-" -> oper.push('m');
                    case "~" -> oper.push('n');
                    case "count" -> oper.push('c');
                    case "flip" -> oper.push('f');
                    case "low" -> oper.push('l');
                }
            }
            ind++;
        }
        while (oper.size() != 0 && num.size() != 0) {
            union(oper, num);
        }
        return num.peek();
    }

    void union(Stack<Character> op, Stack<MultiExpression> num) {
        MultiExpression a = num.peek();
        num.pop();
        char ch = op.peek();
        op.pop();
        if (ch == 'm') {
            num.push(new UnaryMinus(a));
        } else if (ch == 'f') {
            num.push(new Flip(a));
        } else if (ch == 'n') {
            num.push(new Not(a));
        } else if (ch == 'l') {
            num.push(new Low(a));
        } else if (ch == 'c') {
            num.push(new Count(a));
        } else if (num.size() > 0) {
            MultiExpression b = num.peek();
            num.pop();
            if (ch == '+') {
                num.push(new Add(b, a));
            } else if (ch == '-') {
                num.push(new Subtract(b, a));
            } else if (ch == '*') {
                num.push(new Multiply(b, a));
            } else if (ch == '/') {
                num.push(new Divide(b, a));
            } else if (ch == '&') {
                num.push(new And(b, a));
            } else if (ch == '^') {
                num.push(new Xor(b, a));
            } else if (ch == '|') {
                num.push(new Or(b, a));
            } else {
                throw new UnsupportedOperationException("has single bracket");
            }
        } else {
            throw new UnsupportedOperationException("has single bracket");
        }
    }
}

