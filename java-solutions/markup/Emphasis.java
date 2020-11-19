package markup;

import java.util.List;

public class Emphasis extends ComplexText {
    public Emphasis(List<ParagraphElement> a) {
        super(a);
    }

    public String getTag() {
        return "i";
    }

    public String getTagM() {
        return "*";
    }
}
