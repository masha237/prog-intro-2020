package markup;

import java.util.List;

public class Strikeout extends ComplexText {
    public Strikeout(List<ParagraphElement> a) {
        super(a);
    }

    public String getTag() {
        return "s";
    }

    public String getTagM() {
        return "~";
    }
}
