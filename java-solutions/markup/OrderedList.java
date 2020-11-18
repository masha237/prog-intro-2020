package markup;

import java.util.List;

public class OrderedList extends AbstractList implements ListElement {
    public OrderedList(List<ListItem> a) {
        super(a, "", "[list=1]", "[/list]");
    }
}