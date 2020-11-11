package markup;

import java.util.List;

public class Emphasis extends ComplexText {
    public Emphasis(List<ParagraphElement> a) {
        super(a, "*", "[i]", "[/i]");
    }
}
