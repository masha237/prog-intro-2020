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

    public MNKBoard(int row, int col, int k) {
        this.cells = new Cell[row][col];
        for (Cell[] rows : cells) {
            Arrays.fill(rows, Cell.E);
        }
        turn = Cell.X;
        this.col = col;
        this.row = row;
        this.k = k;
        this.maxMove = row * col;
    }

    protected void setCell(int x, int y) {
        maxMove--;
        cells[x][y] = Cell.N;
    }

    @Override
    public Position getPosition() {
        return new ProxyPosition(this);
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
        int[] dx = {-1, 0, 1, -1};
        int[] dy = {-1, -1, -1, 0};
        for (int i = 0; i < 4; i++) {
            int res = getResult(move.getRow(), move.getColumn(), dx[i], dy[i], turn) + getResult(move.getRow(), move.getColumn(), -dx[i], -dy[i], turn) - 1;
            if (res >= k) {
                return Result.WIN;
            }
            if (res >= 4) {
                nextMove = true;
            }
        }
        if (maxMove == 0) {
            return Result.DRAW;
        }
        if (!nextMove) {
            turn = turn == Cell.X ? Cell.O : Cell.X;
            return Result.UNKNOWNNOTSWAP;
        }
        return Result.UNKNOWNSWAP;
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
        int width = Math.max(Math.max((int) Math.ceil(Math.log10(row)), (int) Math.ceil(Math.log10(col))), 1);
        sb.append(String.format("%" + width + "s", ""));
        for (int i = 0; i < col; i++)
            sb.append(String.format("%" + width + "d", i));
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

    protected boolean isPosition(int x, int y) {
        return 0 <= x && x < this.getRow() && 0 <= y && y < this.getCol();
    }


}
