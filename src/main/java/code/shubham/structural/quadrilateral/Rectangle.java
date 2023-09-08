package code.shubham.structural.quadrilateral;

public class Rectangle extends Quadrilateral {

    public Rectangle(final Double x, final Double y, final Double h, final Double w) {
        super(new Point(x, y), new Point(x, y + w), new Point(x + h, y), new Point(x + h, y + w));
    }

    public boolean lies(final Point point) {
        return point.x > bottomLeft.x && point.x < topRight.x && point.y > bottomLeft.y && point.y < topRight.y;
    }

    public boolean lies(final Rectangle r) {
        return this.lies(r.topLeft) || this.lies(r.bottomLeft) || this.lies(r.topRight) || this.lies(r.bottomRight);
    }

    @Override
    public Double area() {
        return (this.bottomRight.x - this.bottomLeft.x) * (topRight.y - bottomRight.y);
    }
}
