import java.io.IOException;
import java.io.Reader;
import java.util.function.Predicate;

public class MyScanner implements AutoCloseable {
    private final Reader reader;
    private final char[] buffer = new char[1 << 15];
    private int index = 0;
    private int sz = 0;

    public MyScanner(Reader a) {
        reader = a;
    }

    public void close() throws IOException {
        reader.close();
    }

    private int getBuf() throws IOException {
        index = 0;
        return reader.read(buffer);
    }

    public int read() throws IOException {
        if (index == sz) {
            sz = getBuf();
        }
        if (sz == -1) {
            return -1;
        }
        char c = buffer[index++];
        if (c == '\r' && nextChar() == '\n') {
            index++;
        }
        return c;
    }

    public boolean hasNextChar() throws IOException {
        if (sz == -1) {
            return false;
        }
        if (index != sz) {
            return true;
        }
        sz = getBuf();
        return sz != -1;
    }

    public int nextChar() throws IOException {
        if (!hasNextChar()) {
            return -1;
        } else {
            return buffer[index];
        }
    }

    private boolean isEndOfLine(int c) {
        return (c == '\n' || c == '\r');
    }

    // :NOTE: Predicate<Character> -> поле
    private String nextToken(Predicate<Character> isWordCharacter) throws IOException {
        StringBuilder sb = new StringBuilder();
        int c;
        while ((c = read()) != -1 && (!isWordCharacter.test((char) c) || isEndOfLine(c))) {
            if (isEndOfLine(c)) {
                return null;
            }
        }
        if (c == -1) {
            return sb.toString();
        }
        do {
            sb.append((char) c);
        } while (!isEndOfLine(nextChar()) && (c = read()) != -1 && isWordCharacter.test((char) c));
        return sb.toString();
    }

    private static boolean charOfWord(char ch) {
        return Character.isLetter(ch) || ch == '\'' || Character.getType(ch) == Character.DASH_PUNCTUATION;
    }

    private static boolean charOfNumber(char ch) {
        return (Character.getType(ch) != Character.SPACE_SEPARATOR);
    }

    public String nextWord() throws IOException {
        return nextToken(MyScanner::charOfWord);
    }

    public Integer nextInt() throws IOException, NumberFormatException {
        String x = nextToken(MyScanner::charOfNumber);
        if (x == null || x.isEmpty()) {
            return null;
        }
        x = x.toUpperCase();
        try {
            return Long.decode(x).intValue();
        } catch (NumberFormatException e) {
            StringBuilder sb = new StringBuilder();
            int sign = 0;
            if (x.charAt(0) == '-' || x.charAt(0) == '+') {
                sign = 1;
                sb.append(x.charAt(0));
            }
            for (int i = sign; i < x.length(); i++) {
                char c = x.charAt(i);
                if ('A' <= c && c <= 'J') {
                    sb.append(c - 'A');
                } else {
                    sb.append(Character.digit(c, 10));
                }
            }
            try {
                return Integer.parseInt(sb.toString());
            } catch (NumberFormatException e2) {
                throw new NumberFormatException(x + " is not number");
            }
        }

    }
}
