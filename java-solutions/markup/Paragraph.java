package markup;

import java.util.List;

public class Paragraph extends AbstractElement implements ListElement {
    public Paragraph(List<ParagraphElement> a) {
        super(a, "", "", "");
    }
}
