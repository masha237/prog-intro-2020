package markup;

import java.util.List;

public abstract class AbstractElement implements Markup {
    private final List<? extends Markup> a;
    private final String tagM, tagBb, tagBe;
    AbstractElement(List<? extends Markup> a, String tagM, String tagBb, String tagBe) {
        this.a = a;
        this.tagM = tagM;
        this.tagBe = tagBe;
        this.tagBb = tagBb;
    }

    public void toMarkdown(StringBuilder sb) {
        sb.append(tagM);
        for (Markup markup : a) {
            markup.toMarkdown(sb);
        }
        sb.append(tagM);
    }

    public void toBBCode(StringBuilder sb) {
        sb.append(tagBb);
        for (Markup markup : a) {
            markup.toBBCode(sb);
        }
        sb.append(tagBe);
    }
}
