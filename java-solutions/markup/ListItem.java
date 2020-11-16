package markup;

import java.util.List;

public class ListItem extends AbstractElement {
    public ListItem(List<ListElement> a) {
        super(a, "", "[*]", "");
    }
}
