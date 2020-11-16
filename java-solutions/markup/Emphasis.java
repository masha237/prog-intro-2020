package markup;

import java.util.List;

public class Emphasis extends AbstractElement implements ParagraphElement {
    public Emphasis(List<ParagraphElement> a) {
        super(a, "*", "[i]", "[/i]");
    }
}
