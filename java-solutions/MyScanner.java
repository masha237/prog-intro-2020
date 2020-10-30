import java.io.IOException;
import java.io.Reader;
import java.util.function.Predicate;

public class MyScanner implements AutoCloseable {
    private Reader reader;
    private char[] buffer = new char[1 << 15];
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

    private String nextToken(Predicate<Character> isWordCharacter) throws IOException {
        StringBuilder sb = new StringBuilder();
        int c = nextChar();

        // :NOTE: Дублирование
        if (c == '\n' || c == '\r') {
            read();
            return null;
        }
        while ((c = read()) != -1 && !isWordCharacter.test((char) c)) {
            if ((char) c == '\n' || (char) c == '\r') {
                read();
                return null;
            }
        }
        if (c == -1) {
            return sb.toString();
        }
        // :NOTE: Выделение функций
        do {
            sb.append((char) c);
        } while ((char) nextChar() != '\n' && (char) nextChar() != '\r' && (c = read()) != -1 && isWordCharacter.test((char) c));
        if (((char) nextChar() == '\n' || (char) nextChar() == '\r') && sb.length() == 0) {
            read();
            return null;
        }
        return sb.toString();
    }

    private static boolean charOfWord(char ch) {
        return Character.isLetter(ch) || ch == '\'' || Character.getType(ch) == Character.DASH_PUNCTUATION;
    }

    // :NOTE: Пробельные символы
    private static boolean charOfNumber(char ch) {
        return (ch != ' ');
    }

    public String nextWord() throws IOException {
        return nextToken(MyScanner::charOfWord);
    }

    public Integer nextInt() throws IOException, NumberFormatException {
        String x = nextToken(MyScanner::charOfNumber);
        if (x == null || x.isEmpty()) {
            return null;
        }
        if (x.length() < 2 || Character.toUpperCase(x.charAt(1)) != 'X') {
            StringBuilder sb = new StringBuilder();
            int sign = 0;
            if (x.charAt(0) == '-' || x.charAt(0) == '+') {
                sign = 1;
            }
            for (int i = sign; i < x.length(); i++) {
                char c = Character.toUpperCase(x.charAt(i));
                if ('A' <= c && c <= 'J') {
                    sb.append(c - 'A');
                } else {
                    if (!Character.isDigit(c)) {
                        throw new NumberFormatException(x + " is not number");
                    }
                    sb.append(Character.digit(c, 10));
                }
            }
            try {
                int q = Integer.parseInt(sb.toString());
                // :NOTE: Отдельная обработка знака??
                if (sign != 0) {
                    q = -q;
                }
                return q;
            } catch (NumberFormatException e) {
                throw new NumberFormatException(x + " is not number");
            }
        }
        try {
            return Long.decode(x).intValue();
        } catch (NumberFormatException e) {
            throw new NumberFormatException(x + " is not number");
        }
    }
}