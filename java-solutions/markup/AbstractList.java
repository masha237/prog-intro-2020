package markup;

import java.util.List;

public abstract class AbstractList extends AbstractElement implements ListElement{
    AbstractList(List<ListItem> a) {
        super(a);
    }

    @Override
    public void toMarkdown(StringBuilder sb) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("List hasn't toMarkdown");
    }

    public String getTagBe() {
        return "[/list]";
    }

    public String getTagM() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}
