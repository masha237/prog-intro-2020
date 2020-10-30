package markup;

import java.util.List;

public class Strikeout extends AbstractElement implements Element {
    public Strikeout(List<Element> a) {
        super(a, "~", "s");
    }
}
