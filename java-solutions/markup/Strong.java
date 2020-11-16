package markup;

import java.util.List;

public class Strong extends AbstractElement implements ParagraphElement {
    public Strong(List<ParagraphElement> a) {
        super(a, "__", "[b]", "[/b]");
    }
}
