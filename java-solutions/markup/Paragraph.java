package markup;

import java.util.List;

public class Paragraph extends AbstractElement implements ListElement {
    public Paragraph(List<ParagraphElement> a) {
        super(a);
    }

    public String getTagM() {
        return "";
    }

    public String getTagBb() {
        return "";
    }

    public String getTagBe() {
        return "";
    }
}
