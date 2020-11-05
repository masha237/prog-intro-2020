package markup;

import java.util.List;

public class Paragraph implements ComplexElement {
    List<AllText> a;
    public Paragraph(List<AllText> a) {
        this.a = a;
    }

    public void toMarkdown(StringBuilder sb) {
        for (AllText element : a) {
            element.toMarkdown(sb);
        }
    }

    public void toBBCode(StringBuilder sb) {
        for (AllText element : a) {
            element.toBBCode(sb);
        }
    }
}
