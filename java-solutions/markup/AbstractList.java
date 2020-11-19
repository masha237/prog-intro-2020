package markup;

import java.util.List;

public abstract class AbstractList extends AbstractElement implements ListElement{
    AbstractList(List<ListItem> a) {
        super(a);
    }

    public String getTagBe() {
        return "[/list]";
    }

    public String getTagM() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("List hasn't toMarkdown");
    }
}
