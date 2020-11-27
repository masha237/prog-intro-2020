package ticTacToe;


import java.util.Scanner;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Main {
    static Scanner in = new Scanner(System.in);

    public static int scan(String s, Scanner in) {
        System.out.println("Enter " + s + ":");
        while (true) {
            try {
                while (!in.hasNextInt()) {
                    System.out.println(in.next() + "is not number, try again");
                }
                return in.nextInt();
                // :NOTE: Exception
            } catch (Exception e) {
                System.out.println(e.getMessage() + " no more tokens are available");
                // :NOTE: System.exit(0);
                System.exit(0);
            }
        }
    }

    private static int rhombusGame(Game game) {
        int n = scan("n", in);
        int k = scan("k", in);
        return game.play(new RhombusBoard(n, k));
    }

    private static int rectangleGame(Game game) {
        int m = scan("m", in);
        int n = scan("n", in);
        int k = scan("k", in);
        return game.play(new RectangleBoard(n, m, k));
    }

    public static void main(String[] args) {
        final Game game = new Game(false, new HumanPlayer(), new HumanPlayer());
        int result;
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
    }
}
