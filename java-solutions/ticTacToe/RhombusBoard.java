package ticTacToe;

public class RhombusBoard extends MNKBoard {
    public RhombusBoard(int a, int k) {
        super(2 * a - 1, 2 * a - 1, k);
        int c = 2 * a - 1;
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < c; j++) {
                if (dist(i, c / 2) + dist(j, c / 2) > c / 2) {
                    setCell(i, j);
                }
            }
        }
    }
    private int dist(int i, int c) {
        return Math.abs(i - c);
    }
}
