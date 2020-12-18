package expression.parser;

import java.util.ArrayList;
import java.util.Set;
import java.util.function.Predicate;

public class MakerTokens {
    private static StringSource in;
    private final Predicate<Character> isTokenCharacter;
    private final Set<String> isToken;
    public MakerTokens(String expression, Predicate<Character> isTokenCharacter, Set<String> isToken) {
        in = new StringSource(expression);
        this.isTokenCharacter = isTokenCharacter;
        this.isToken = isToken;
    }

    public ArrayList<String> getAllToken() {
        ArrayList<String> res = new ArrayList<>();
        while (hasNext()) {
            res.add(nextToken());
        }
        return res;
    }

    public String nextToken() {
        StringBuilder sb = new StringBuilder();
        while (hasNext()) {
            sb.append(in.next());
            if (isToken.contains(sb.toString())) {
                return sb.toString();
            } else if (Character.isDigit(sb.charAt(0))) {
                while (Character.isDigit(in.getNext())) {
                    sb.append(in.next());
                }
                return sb.toString();
            }
        }
        return "";
    }

    public boolean hasNext() {
        skip();
        return in.hasNext();
    }
    private void skip() {
        while (!this.isTokenCharacter.test(in.getNext())) {
            in.next();
        }
    }
}
