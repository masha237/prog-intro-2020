package expression;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int x = scan.nextInt();
        String res = new Subtract(new Const(1), new Subtract(new Const(2), new Const(3))).toMiniString(); // (1+((x*x)-(2*x)))
        System.out.println(res);
    }
}
