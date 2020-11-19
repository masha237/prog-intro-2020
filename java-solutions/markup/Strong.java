package markup;

import java.util.List;

public class Strong extends ComplexText {
    public Strong(List<ParagraphElement> a) {
        super(a);
    }

    public String getTag() {
        return "b";
    }

    public String getTagM() {
        return "__";
    }
}
