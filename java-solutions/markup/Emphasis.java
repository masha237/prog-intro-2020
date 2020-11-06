package markup;

import java.util.List;

public class Emphasis extends ComplexText {
    public Emphasis(List<AllText> a) {
        super(a, "*", "i");
    }
}
