package ticTacToe;

public class RhombusBoard extends MNKBoard {
    public RhombusBoard(int a, int k) {
        super(2 * a - 1, 2 * a - 1, k);
        int c = 2 * a - 1;
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < c; j++) {
                if (Math.abs(i - c / 2) + Math.abs(j - c / 2) > c / 2) {
                    setCell(i, j);
                }
            }
        }
    }
}
