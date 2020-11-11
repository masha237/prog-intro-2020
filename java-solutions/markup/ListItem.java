package markup;

import java.util.List;

public class ListItem implements BBCode {
    private final List<ListElement> a;
    
    public ListItem(List<ListElement> a) {
        this.a = a;
    }

    public void toBBCode(StringBuilder sb) {
        Util.toBBCode(sb, a, "[*]", "");
    }
}
