package markup;

import java.util.List;

public class Paragraph extends AbstractElement implements Element, ListElement {
    public Paragraph(List<Element> a) {
        super(a, "", "");
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        for (Element element : a) {
            element.toBBCode(sb);
        }
    }
}
