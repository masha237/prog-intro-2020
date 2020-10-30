package markup;

import java.util.List;

public class AbstractList implements ListElement{
    List<ListItem> a;
    String tag;
    AbstractList (List<ListItem> a, String tag) {
        this.a = a;
        this.tag = tag;
    }
    @Override
    public void toBBCode(StringBuilder sb) {
        sb.append(tag);
        for (ListItem listItem : a) {
            listItem.toBBCode(sb);
        }
        sb.append("[/list]");
    }
}
