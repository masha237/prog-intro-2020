package markup;

import java.util.List;

public class ListItem extends AbstractElement {
    public ListItem(List<ListElement> a) {
        super(a);
    }

    public String getTagBb() {
        return "[*]";
    }

    public String getTagBe() {
        return "";
    }

    public String getTagM() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}
