package markup;

import java.util.List;

public class ListItem implements ListElement {
    List<ListElement> a;
    ListItem(List<ListElement> a) {
        this.a = a;
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        for (ListElement listElement : a) {
            sb.append("[*]");
            listElement.toBBCode(sb);
        }
    }
}
