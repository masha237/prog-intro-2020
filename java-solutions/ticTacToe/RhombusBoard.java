package ticTacToe;

public class RhombusBoard extends MNKBoard {
    public RhombusBoard(int a, int k) {
        super(2 * a - 1, 2 * a - 1, k, a * a + (a - 1) * (a - 1));

        // :NOTE: Упростить
        for (int i = 0; i < a - 1; i++) {
            for (int j = 0; j < a - i - 1; j++) {
                setCell(i, j);
            }
        }
        for (int i = 0; i < a - 1; i++) {
            for (int j = i + a; j < 2 * a - 1; j++) {
                setCell(i, j);
            }
        }
        for (int i = a; i < 2 * a - 1; i++) {
            for (int j = 0; j < i - a + 1; j++) {
                setCell(i, j);
            }
        }
        for (int i = a; i < a * 2 - 1; i++) {
            for (int j = 2 * a - 1 - (i - a) - 1; j < a * 2 - 1; j++) {
                setCell(i, j);
            }
        }
    }

    protected boolean isPosition(int x, int y) {
        return 0 <= x && x < this.getRow() && 0 <= y && y < this.getCol();
    }
}
