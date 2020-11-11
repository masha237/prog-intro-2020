package markup;

import java.util.List;

public class Util {
    public static void toMarkdown(StringBuilder sb, List<? extends Markdown> data, String bracket) {
        sb.append(bracket);
        for (Markdown element : data) {
            element.toMarkdown(sb);
        }
        sb.append(bracket);
    }

    public static void toBBCode(StringBuilder sb, List<? extends BBCode> data, String bracketb, String brackete) {
        sb.append(bracketb);
        for (BBCode element : data) {
            element.toBBCode(sb);
        }
        sb.append(brackete);
    }
}
