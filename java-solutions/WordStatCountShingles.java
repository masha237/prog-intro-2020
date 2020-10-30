import java.io.*;
import java.util.*;
import java.nio.charset.StandardCharsets;

public class WordStatCountShingles {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.print("args size != 2");
            return;
        }
        int N = 3;
        int ind = 0;
        Map<String, Integer> mp = new LinkedHashMap<>();
        try (MyScanner reader = new MyScanner(new FileReader(args[0], StandardCharsets.UTF_8))) {
            String word;
            while (reader.nextChar() != -1) {
                word = reader.nextWord();
                if (word == null) {
                    continue;
                }
                char[] a = new char[N];
                for (int i = 0; i < word.length(); i++) {
                    a[ind] = Character.toLowerCase(word.charAt(i));
                    ind++;
                    if (ind == N) {
                        String s = new String(a);
                        mp.put(s, mp.getOrDefault(s, 0) + 1);
                        System.arraycopy(a, 1, a, 0, N - 1);
                        ind--;
                    }
                }
                ind = 0;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Don't have input file: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Cannot read file: " + e.getMessage());
        }

        List<Map.Entry<String, Integer>> pairs = new ArrayList<>(mp.entrySet());
        pairs.sort(Comparator.comparingInt(Map.Entry::getValue));

        try (Writer writer = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8))) {
            for (Map.Entry<String, Integer> pair : pairs) {
                writer.write(pair.getKey() + " " + pair.getValue());
                writer.write(System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            System.out.println("No access to file: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Cannot write file: " + e.getMessage());
        }
    }
}
