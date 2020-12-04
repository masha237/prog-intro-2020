package ticTacToe;


import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Main {
    static Scanner in = new Scanner(System.in);

    private static int rhombusGame(Game game) throws IllegalStateException, NoSuchElementException {
        int n = Scan.scan("n", in);
        int k = Scan.scan("k", in);
        return game.play(new RhombusBoard(n, k));
    }

    private static int rectangleGame(Game game) throws IllegalStateException, NoSuchElementException {
        int m = Scan.scan("m", in);
        int n = Scan.scan("n", in);
        int k = Scan.scan("k", in);
        return game.play(new RectangleBoard(n, m, k));
    }

    public static void main(String[] args) {
        final Game game = new Game(false, new HumanPlayer(), new HumanPlayer(), 2);
        int result;
        try {
            do {
                while (true) {
                    System.out.println("Enter board's name");
                    String s = in.next();
                    if (s.equals("Rectangle")) {
                        result = rectangleGame(game);
                        break;
                    } else if (s.equals("Rhombus")) {
                        result = rhombusGame(game);
                        break;
                    } else {
                        System.out.println(s + " isn't board's name. Try again.");
                    }
                }
                System.out.println("Game result: " + result);
            } while (result != 0);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage() + " Your scanner was closed");
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage() + " You don't enter words");
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }
}
