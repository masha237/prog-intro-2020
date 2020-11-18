package markup;

import java.util.List;

public class UnorderedList extends AbstractList implements ListElement {
    public UnorderedList(List<ListItem> a) {
        super(a, "", "[list]", "[/list]");
    }
}

