package markup;

import java.util.List;

public abstract class ComplexText extends AbstractElement implements ParagraphElement{
    public ComplexText(List<ParagraphElement> a) {
        super(a);
    }

    public String getTagBe() {
        return "[/" + getTag() + "]";
    }

    public String getTagBb() {
        return "[" + getTag() + "]";
    }

    protected abstract String getTag();
}
