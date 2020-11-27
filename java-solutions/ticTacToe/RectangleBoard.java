package ticTacToe;

public class RectangleBoard extends MNKBoard {
    public RectangleBoard(int row, int col, int k) {
        super(row, col, k);
    }

    protected boolean isPosition(int x, int y) {
        return 0 <= x && x < this.getRow() && 0 <= y && y < this.getCol();
    }


}
