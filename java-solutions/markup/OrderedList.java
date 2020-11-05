package markup;

import java.util.List;

public class OrderedList extends AbstractComplexElement {
    public OrderedList(List<ListItem> a) {
        super(a, "[list=1]");
    }
}