package markup;

import java.util.List;

public class UnorderedList extends AbstractComplexElement {
    public UnorderedList(List<ListItem> a) {
        super(a, "[list]");
    }
}
