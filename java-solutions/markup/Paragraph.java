package markup;

import java.util.List;

public class Paragraph implements ListElement, Markdown {
    private final List<ParagraphElement> a;

    public Paragraph(List<ParagraphElement> a) {
        this.a = a;
    }

    public void toMarkdown(StringBuilder sb) {
        Util.toMarkdown(sb, a, "");
    }

    public void toBBCode(StringBuilder sb) {
        Util.toBBCode(sb, a, "", "");
    }
}
