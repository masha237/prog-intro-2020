package markup;

import java.util.List;

public class Emphasis extends AbstractText {
    public Emphasis(List<AllText> a) {
        super(a, "*", "i");
    }
}
