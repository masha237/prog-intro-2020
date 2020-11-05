package markup;

import java.util.List;

public abstract class AbstractList implements ComplexElement {
    protected final List<ListItem> a;
    private final String tagBBC;

    public AbstractList(List<ListItem> a, String tagBBC) {
        this.a = a;
        this.tagBBC = tagBBC;
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        sb.append(tagBBC);
        for (ListItem element : a) {
            element.toBBCode(sb);
        }
        sb.append("[/list]");
    }
}
