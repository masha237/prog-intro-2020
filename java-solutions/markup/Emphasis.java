package markup;

import java.util.List;

public class Emphasis extends AbstractElement implements Element {
    public Emphasis(List<Element> a) {
        super(a, "*", "i");
    }
}
