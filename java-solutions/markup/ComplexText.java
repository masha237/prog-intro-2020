package markup;

import java.util.List;

public abstract class ComplexText implements ParagraphElement {
    protected final List<ParagraphElement> a;
    private final String tagMD;
    private final String tagBBCb;
    private final String tagBBCe;

    public ComplexText(List<ParagraphElement> a, String tagMD, String tagBBCb, String tagBBCe) {
        this.a = a;
        this.tagMD = tagMD;
        this.tagBBCb = tagBBCb;
        this.tagBBCe = tagBBCe;
    }

    public void toBBCode(StringBuilder sb) {
        Util.toBBCode(sb, a, tagBBCb, tagBBCe);
    }

    public void toMarkdown(StringBuilder sb) {
        Util.toMarkdown(sb, a, tagMD);
    }
}
