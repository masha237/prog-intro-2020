package expression.parser;

import expression.*;

import java.util.Map;
import java.util.Stack;

public class ExpressionParser implements Parser {
    Map<Character, Integer> mp = Map.of(
            '-', 1,
            '+', 1,
            '*', 2,
            '/', 2
    );

    boolean isOperation(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    @Override
    public TripleExpression parse(String expression) {
        Stack<Character> oper = new Stack<>();
        Stack<MultiExpression> num = new Stack<>();
        int ind = 0;
        boolean fl = false;
        int sign = 1;
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
            } else if (isOperation(expression.charAt(ind))) {
                if (fl) {
                    sign *= -1;
                    ind++;
                    continue;
                }
                int prior = mp.get(expression.charAt(ind));
                while (!oper.empty() && isOperation(oper.peek()) && prior <= mp.get(oper.peek())) {
                    union(oper, num);
                }
                fl = true;
                oper.push(expression.charAt(ind));
            } else if (Character.isDigit(expression.charAt(ind))) {
                fl = false;
                int cur = 0;
                while (ind != expression.length() && Character.isDigit(expression.charAt(ind))) {
                    cur = 10 * cur + expression.charAt(ind) - '0';
                    ind++;
                }
                ind--;
                num.push(new Const(cur * sign));
                sign = 1;
            } else if (Character.isAlphabetic(expression.charAt(ind))) {
                fl = false;
                StringBuilder sb = new StringBuilder();
                while (ind != expression.length() && Character.isAlphabetic(expression.charAt(ind))) {
                    sb.append(expression.charAt(ind));
                    ind++;
                }
                ind--;
                num.push(new Variable(sb.toString()));
            }
            ind++;
        }
        while (num.size() != 1) {
            union(oper, num);
        }
        return num.peek();
    }
    void union (Stack<Character> op, Stack<MultiExpression> num) {
        MultiExpression a = num.peek();
        num.pop();
        MultiExpression b = num.peek();
        num.pop();
        char ch = op.peek();
        op.pop();
        if (ch == '+') {
            num.push(new Add(b, a));
        } else if (ch == '-') {
            num.push(new Subtract(b, a));
        } else if (ch == '*') {
            num.push(new Multiply(b, a));
        } else {
            num.push(new Divide(b, a));
        }
    }
}
