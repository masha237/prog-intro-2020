package markup;

import java.util.List;

public class Strikeout extends AbstractElement implements ParagraphElement {
    public Strikeout(List<ParagraphElement> a) {
        super(a, "~", "[s]", "[/s]");
    }
}

