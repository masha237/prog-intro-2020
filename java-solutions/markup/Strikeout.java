package markup;

import java.util.List;

public class Strikeout extends ComplexText {
    public Strikeout(List<AllText> a) {
        super(a, "~", "s");
    }
}
