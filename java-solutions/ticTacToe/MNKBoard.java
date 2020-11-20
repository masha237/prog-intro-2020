package ticTacToe;

import java.util.Arrays;
import java.util.Map;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public abstract class MNKBoard implements Board, Position {
    protected static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.',
            Cell.N, ' '
    );

    private final Cell[][] cells;
    private Cell turn;
    private final int row, col, k;
    private int maxMove;
    public MNKBoard(int row, int col, int k, int maxMove) {
        this.cells = new Cell[row][col];
        for (Cell[] rows : cells) {
            Arrays.fill(rows, Cell.E);
        }
        turn = Cell.X;
        this.col = col;
        this.row = row;
        this.k = k;
        this.maxMove = maxMove;
    }

    protected void setCell(int x, int y) {
        cells[x][y] = Cell.N;
    }
    @Override
    public Position getPosition() {
        return new CopyPosition(this);
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }
        maxMove--;
        cells[move.getRow()][move.getColumn()] = move.getValue();
        boolean nextMove = false;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int res = getResult(move.getRow(), move.getColumn(), i, j, turn) + getResult(move.getRow(), move.getColumn(), -i, -j, turn) - 1;
                if (res >= k) {
                    return Result.WIN;
                }
                if (res >= 4)
                    nextMove = true;
            }
        }
        if (maxMove == 0) {
            return Result.DRAW;
        }
        if (!nextMove)
            turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }

    private int getResult(int x, int y, int dx, int dy, Cell cell) {
        int cnt = 0;
        while (isPosition(x, y)) {
            if (getCell(x, y) == cell) {
                cnt++;
            } else {
                break;
            }
            x += dx;
            y += dy;
        }
        return cnt;
    }

    @Override
    public boolean isValid(final Move move) {
        return isPosition(move.getRow(), move.getColumn())
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getCell();
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int width = Math.max((int)Math.ceil(Math.log10(row)), (int)Math.ceil(Math.log10(col)));
        sb.append(String.format("%" + width + "s", ""));
        for (int i = 0; i < col; i++)
            sb.append(String.format("%" + width + "d",i));
        for (int r = 0; r < row; r++) {
            sb.append("\n");
            sb.append(String.format("%" + width + "d", r));
            for (int c = 0; c < col; c++) {
                sb.append(String.format("%" + width + "c", SYMBOLS.get(cells[r][c])));
            }
        }
        return sb.toString();
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getK() {
        return k;
    }

    protected abstract boolean isPosition(int x, int y);


}
