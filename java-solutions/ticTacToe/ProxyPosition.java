package ticTacToe;

public class ProxyPosition implements Position {

    private Position position;

    public ProxyPosition(Position position) {
        this.position = position;
    }
    @Override
    public boolean isValid(Move move) {
        return position.isValid(move);
    }

    @Override
    public Cell getCell(int r, int c) {
        return position.getCell(r, c);
    }

    @Override
    public int getRow() {
        return position.getRow();
    }

    @Override
    public int getCol() {
        return position.getCol();
    }

    @Override
    public String toString() {
        return position.toString();
    }
}
