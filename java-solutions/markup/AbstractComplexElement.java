package markup;

import java.util.List;

public abstract class AbstractComplexElement implements ComplexElement {
    protected final List<ListItem> a;
    private final String tagBCb;

    public AbstractComplexElement(List<ListItem> a, String tagBCb) {
        this.a = a;
        this.tagBCb = tagBCb;
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        sb.append(tagBCb);
        for (ListItem element : a) {
            element.toBBCode(sb);
        }
        sb.append("[/list]");
    }
}
