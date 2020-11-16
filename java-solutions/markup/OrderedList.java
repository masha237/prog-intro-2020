package markup;

import java.util.List;

public class OrderedList extends AbstractElement implements ListElement {
    public OrderedList(List<ListItem> a) {
        super(a, "", "[list=1]", "[/list]");
    }
}