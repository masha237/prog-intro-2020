package markup;

import java.util.List;

public abstract class AbstractList extends AbstractElement implements ListElement{
    AbstractList(List<ListItem> a, String tagM, String tagBb, String tagBe) {
        super(a, tagM, tagBb, tagBe);
    }

    @Override
    public void toMarkdown(StringBuilder sb) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("List hasn't toMarkdown");
    }
}
