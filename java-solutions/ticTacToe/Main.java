package ticTacToe;


import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Main {
    static Scanner in = new Scanner(System.in);
    public static int scan(String s) {
        System.out.println("Enter " + s + ":");
        String k = in.next();
        while (true) {
            try {
                Integer.parseInt(k);
                break;
            } catch (NumberFormatException e) {
                System.out.println(k + "is not number, try again");
                k = in.next();
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage() + "no more tokens are available");
            }
        }
        return Integer.parseInt(k);
    }
    public static void main(String[] args) {
        final Game game = new Game(false, new RandomPlayer(), new RandomPlayer());
        int result;
        do {
            int m = scan("m");
            int n = scan("n");
            int k = scan("k");
            result = game.play(new MNKBoard(n, m, k));
            System.out.println("Game result: " + result);
        } while (result != 0);
    }
}
