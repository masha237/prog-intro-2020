package markup;

import java.util.List;

public class OrderedList extends AbstractList {
    public OrderedList(List<ListItem> a) {
        super(a);
    }

    public String getTagBb() {
        return "[list=1]";
    }
}