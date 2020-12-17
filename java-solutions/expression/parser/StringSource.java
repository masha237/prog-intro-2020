package expression.parser;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class StringSource implements CharSource {
    private final String data;
    private int pos;
    private static final char EOF = 0;

    public StringSource(final String data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        skipSpace();
        return pos < data.length();
    }

    @Override
    public char next() {
        return data.charAt(pos++);
    }

    private void skipSpace() {
        while (pos < data.length() && Character.isWhitespace(data.charAt(pos))) {
            next();
        }
    }

    public char getNext() {
        return hasNext() ? data.charAt(pos) : EOF;
    }

    public boolean test(char ch) {
        if (getNext() == ch) {
            next();
            return true;
        }
        return false;
    }

    public boolean test(String s) {
        int index = pos;
        for (int i = 0; i < s.length(); i++) {
            if (!test(s.charAt(i))) {
                pos = index;
                return false;
            }
        }
        return true;
    }

    @Override
    public ParseException error(final String message) {
        return new ParseException(pos + ": " + message);
    }
}
