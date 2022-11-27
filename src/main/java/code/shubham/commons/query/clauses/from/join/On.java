package code.shubham.commons.query.clauses.from.join;

public class On {

    private final String[] xField;
    private final String[] yField;

    public On(String xField, String yField) {
        this(xField.split("\\."), yField.split("\\."));
    }

    public On(String[] xField, String[] yField) {
        this.xField = xField;
        this.yField = yField;
    }

    public String[] getxField() {
        return xField;
    }

    public String[] getyField() {
        return yField;
    }
}
