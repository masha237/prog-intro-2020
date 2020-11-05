package markup;

import java.util.List;

public abstract class AbstractText implements AllText {
    protected final List<AllText> a;
    private final String tagMD;
    private final String tagBCb;

    public AbstractText(List<AllText> a, String tagMD, String tagBCb) {
        this.a = a;
        this.tagMD = tagMD;
        this.tagBCb = tagBCb;
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(tagMD);
        for (AllText element : a) {
            element.toMarkdown(sb);
        }
        sb.append(tagMD);
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        sb.append("[").append(tagBCb).append("]");
        for (AllText element : a) {
            element.toBBCode(sb);
        }
        sb.append("[/").append(tagBCb).append("]");
    }
}
