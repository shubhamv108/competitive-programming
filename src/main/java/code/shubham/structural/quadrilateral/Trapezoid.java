package code.shubham.structural.quadrilateral;

public class Trapezoid extends Quadrilateral {
    public Trapezoid(final Point topLeft, final Point bottomLeft, Double height, Double width, Double topAngle, Double bottomAngle) {
        super(topLeft, new Point(topLeft.x + width, topLeft.y), bottomLeft, new Point(bottomLeft.x + width, topLeft.y + height));
    }

    @Override
    public Double area() {
        return null;
    }
}
