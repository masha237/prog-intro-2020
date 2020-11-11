import java.nio.charset.StandardCharsets;
import java.io.*;
import java.util.*;

public class WordStatCountFirstIndex {
    public static String getAns(IntList a) {
        StringBuilder sb = new StringBuilder();
        sb.append(a.getVal(0));
        for (int i = 1; i < a.getSz(); i++) {
            if (a.getValLine(i) != a.getValLine(i - 1)) {
                sb.append(" ").append(a.getVal(i));
            }
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.print("args size != 2");
            return;
        }
        //:NOTES: just renumerate indexs
        Map<String, IntList> mp = new LinkedHashMap<>();
        try (MyScanner reader = new MyScanner(new FileReader(args[0], StandardCharsets.UTF_8))) {
            String word;
            int ind = 0, number = 0, line = 0;
            while (reader.nextChar() != -1) {
                word = reader.nextWord();
                if (word == null) {
                    line++;
                    number = 0;
                }
                if (word == null || word.isEmpty()) {
                    continue;
                }
                word = word.toLowerCase();
                number++;
                if (!mp.containsKey(word)) {
                    mp.put(word, new IntList());
                }
                mp.get(word).add(number, line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Don't have input file: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Cannot read file: " + e.getMessage());
        }
        List<Map.Entry<String, IntList>> pairs = new ArrayList<>(mp.entrySet());

        pairs.sort(Comparator.comparingInt((Map.Entry<String, IntList> p) -> p.getValue().getSz()));
        try (Writer writer = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8))) {
            for (Map.Entry<String, IntList> i : pairs) {
                writer.write(i.getKey());
                writer.write(" ");
                writer.write(i.getValue().getSz() + "");
                writer.write(" ");
                writer.write(getAns(i.getValue()));
                writer.write(System.lineSeparator());
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("No access to file: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Cannot write file: " + e.getMessage());
        }
    }
}

