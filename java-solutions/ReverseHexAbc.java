//import java.util.Scanner;
import java.util.Arrays;
import java.io.*;


public class ReverseHexAbc {
    public static void main(String[] args) {
        int index = 0;
        int[][] b = new int[1][];
        int[] ind = new int[1];
        try (MyScanner scanner = new MyScanner(new InputStreamReader(System.in))) {
            while (scanner.nextChar() != -1) {
                int j = 0;
                if (index == b.length) {
                    b = Arrays.copyOf(b, b.length * 2);
                    ind = Arrays.copyOf(ind, ind.length * 2);
                }
                b[index] = new int[1];
                while (true) {
                    Integer x = scanner.nextInt();
                    if (x == null) {
                        break;
                    }
                    if (j == b[index].length) {
                        b[index] = Arrays.copyOf(b[index], b[index].length * 2);
                    }
                    b[index][j] = x;
                    j++;
                }
                ind[index] = j;
                index++;
            }
        } catch (NumberFormatException | IOException e) {
            System.out.println("Input error: " + e.getMessage());
        }
        try (BufferedWriter w = new BufferedWriter(new OutputStreamWriter(System.out))) {
            for (int i = index - 1; i >= 0; i--) {
                for (int j = ind[i] - 1; j >= 0; j--) {
                    w.write(b[i][j] + " ");
                }
                w.newLine();
            }
        } catch (IOException e) { 
            System.out.println("Output error: " + e.getMessage());
        }
    }
}
