package markup;

import java.util.List;

public class Strikeout extends AbstractText {
    public Strikeout(List<AllText> a) {
        super(a, "~", "s");
    }
}
