package markup;

import java.util.List;

public class ListItem {
    List<ComplexElement> a;
    public ListItem(List<ComplexElement> a) {
        this.a = a;
    }

    public void toBBCode(StringBuilder sb) {
        sb.append("[*]");
        for (ComplexElement element : a) {
            element.toBBCode(sb);
        }
    }
}
