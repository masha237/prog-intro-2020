import java.util.Arrays;
import java.util.Comparator;

public class IntList implements Comparable<IntList> {
    private int[] a;
    private int[] line;
    private int sz;
    private int index;

    public IntList() {
        a = new int[1];
        line = new int[1];
        sz = 0;
        index = 0;
    }

    public int getIndex() {
        return index;
    }

    public int getSz() {
        return sz;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (sz != 0)
            sb.append(a[0]);
        for (int i = 1; i < sz; i++) {
            sb.append(" " + a[i]);
        }
        return sb.toString();
    }

    public int[] getA() {
        return a;
    }

    public int[] getLine() {
        return line;
    }

    public int getVal(int i) throws IndexOutOfBoundsException {
        return a[i];
    }

    public int getValLine(int i) throws IndexOutOfBoundsException {
        return line[i];
    }

    private void expand() {
        a = Arrays.copyOf(a, a.length * 2);
        line = Arrays.copyOf(line, line.length * 2);
    }

    private void nerrow() {
        a = Arrays.copyOf(a, a.length / 2);
        line = Arrays.copyOf(line, line.length / 2);
    }

    public void add(int x, int line_) {
        a[sz] = x;
        line[sz++] = line_;
        if (a.length == sz) {
            expand();
        }
        if (a.length >= sz * 4) {
            nerrow();
        }
    }


    @Override
    public int compareTo(IntList intList) {
        return Integer.compare(sz, intList.getSz());
    }
}

