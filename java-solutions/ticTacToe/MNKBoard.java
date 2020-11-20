package ticTacToe;

import java.util.Arrays;
import java.util.Map;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class MNKBoard implements Board, Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );

    private final Cell[][] cells;
    private Cell turn;
    private final int row, col, k;
    private int number = 0;
    public MNKBoard(int row, int col, int k) {
        this.cells = new Cell[row][col];
        for (Cell[] rows : cells) {
            Arrays.fill(rows, Cell.E);
        }
        turn = Cell.X;
        this.col = col;
        this.row = row;
        this.k = k;
    }

    @Override
    public Position getPosition() {
        return copy();
    }

    private Position copy() {
        MNKBoard copyBoard = new MNKBoard(row, col, k);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                copyBoard.cells[i][j] = getCell(i, j);
            }
        }
        return copyBoard;
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
        number++;
        cells[move.getRow()][move.getColumn()] = move.getValue();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (getResult(move.getRow(), move.getColumn(), i, j, turn) + getResult(move.getRow(), move.getColumn(), -i, -j, turn) - 1 >= k) {
                    return Result.WIN;
                }
            }
        }
        if (number == col * row) {
            return Result.DRAW;
        }
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

    private boolean isPosition(int x, int y) {
        return 0 <= x && x < row && 0 <= y && y < col;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < row
                && 0 <= move.getColumn() && move.getColumn() < col
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

}
