package markup;

import java.util.List;

public class AbstractElement implements Element {
    protected final List<Element> a;
    private final String tagMD;
    private final String tagBCb;

    public AbstractElement(List<Element> a, String tagMD, String tagBCb) {
        this.a = a;
        this.tagMD = tagMD;
        this.tagBCb = tagBCb;
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(tagMD);
        for (Element element : a) {
            element.toMarkdown(sb);
        }
        sb.append(tagMD);
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        sb.append("[").append(tagBCb).append("]");
        for (Element element : a) {
            element.toBBCode(sb);
        }
        sb.append("[/").append(tagBCb).append("]");
    }
}
