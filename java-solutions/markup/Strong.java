package markup;

import java.util.List;

public class Strong extends AbstractElement implements Element {
    public Strong(List<Element> a) {
        super(a, "__", "b");
    }
}
