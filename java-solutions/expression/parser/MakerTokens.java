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
        skip();
        while (in.hasNext()) {
            res.add(nextToken());
        }
        return res;
    }

    public String nextToken() {
        StringBuilder sb = new StringBuilder();
        skip();
        while (in.hasNext()) {
            sb.append(in.next());
            if (isToken.contains(sb.toString())) {
                skip();
                return sb.toString();
            } else if (Character.isDigit(sb.charAt(0))) {
                while (Character.isDigit(in.getNext())) {
                    sb.append(in.next());
                }
                skip();
                return sb.toString();
            }
        }
        throw new UnsupportedOperationException(sb.toString() + " is invalid token");
    }

    void skip() {
        while (!this.isTokenCharacter.test(in.getNext())) {
            in.next();
        }
    }
}
