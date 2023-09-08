package code.shubham.structural.quadrilateral;

public abstract class Quadrilateral {
    public final Point topLeft, topRight, bottomLeft, bottomRight;

    public Quadrilateral(final Point topLeft, final Point topRight, final Point bottomLeft, final Point bottomRight) {
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }

    abstract public Double area();
}
