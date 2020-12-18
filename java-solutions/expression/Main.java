package expression;

import expression.exceptions.ExpressionException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ExpressionException {
        Scanner scan = new Scanner(System.in);
        int x = scan.nextInt();
        int res = new Add(new Multiply(new Variable("x"), new Variable("x")), new Add(new Multiply(new Const(2), new Variable("x")), new Const(1))).evaluate(x);
        System.out.println(res);
    }
}
