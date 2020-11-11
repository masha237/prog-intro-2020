package markup;

import java.util.List;

public abstract class AbstractList implements ListElement {
    protected final List<ListItem> a;
    private final String tagBeg, tagEnd;

    AbstractList(List<ListItem> a, String tagBeg, String tagEnd) {
        this.a = a;
        this.tagBeg = tagBeg;
        this.tagEnd = tagEnd;
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        Util.toBBCode(sb, a, tagBeg, tagEnd);
    }
}
