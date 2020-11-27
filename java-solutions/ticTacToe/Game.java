package ticTacToe;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Game {
    private final boolean log;
    private final Player[] players;
    private final int numberPlayer;

    public Game(final boolean log, final Player player1, final Player player2, final int numberPlayer) {
        this.log = log;
        this.numberPlayer = numberPlayer;
        players = new Player[numberPlayer];
        this.players[0] = player1;
        this.players[1] = player2;
    }

    public int play(Board board) {
        int result;
        int ind = 0;

        while (true) {
            // :NOTE: Копипаста
            result = -2;
            while (result == -2) {
                result = move(board, players[ind], ind + 1);
                if (result >= 0) {
                    System.out.println("Final Board:");
                    System.out.println(board);
                    return result;
                }
            }
            ind = (ind + 1) % numberPlayer;
        }
    }

    private int move(final Board board, final Player player, final int no) {
        final Move move = player.move(board.getPosition(), board.getCell());
        final Result result = board.makeMove(move);
        log("Player " + no + " move: " + move);
        log("Position:\n" + board);
        if (result == Result.WIN) {
            log("Player " + no + " won");
            return no;
        } else if (result == Result.LOSE) {
            log("Player " + no + " lose");
            return 3 - no;
        } else if (result == Result.DRAW) {
            log("Draw");
            return 0;
        } else if (result == Result.UNKNOWN_SWAP) {
            return -1;
        } else {
            return -2;
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}
