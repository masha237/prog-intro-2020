package ticTacToe;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Scan {
    public static int scan(String s, Scanner in) throws IllegalStateException, NoSuchElementException {
        System.out.println("Enter " + s + ":");
        while (true) {
            while (in.hasNext() && !in.hasNextInt()) {
                System.out.println(in.next() + "is not number, try again");
            }
            int x = in.nextInt();
            if (x >= 0) {
                return x;
            } else {
                System.out.println(x + " negative number, try again");
            }
        }
    }
}

