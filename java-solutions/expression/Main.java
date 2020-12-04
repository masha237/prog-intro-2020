package expression;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int x = scan.nextInt();
        String res = new Add(new Multiply(new Variable("x"), new Variable("x")), new Add(new Multiply(new Const(2), new Variable("x")), new Const(1))).toMiniString();
        System.out.println(res);
    }
}
