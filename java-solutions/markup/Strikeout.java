package markup;

import java.util.List;

public class Strikeout extends ComplexText {
    public Strikeout(List<ParagraphElement> a) {
        super(a, "~", "[s]", "[/s]");
    }
}
