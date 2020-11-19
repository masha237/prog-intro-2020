package markup;

import java.util.List;

public abstract class AbstractElement implements Markup {
    private final List<? extends Markup> a;

    AbstractElement(List<? extends Markup> a) {
        this.a = a;
    }

    public void toMarkdown(StringBuilder sb) {
        sb.append(getTagM());
        for (Markup markup : a) {
            markup.toMarkdown(sb);
        }
        sb.append(getTagM());
    }

    protected abstract String getTagM();

    public void toBBCode(StringBuilder sb) {
        sb.append(getTagBb());
        for (Markup markup : a) {
            markup.toBBCode(sb);
        }
        sb.append(getTagBe());
    }

    protected abstract String getTagBb();

    protected abstract String getTagBe();

}
