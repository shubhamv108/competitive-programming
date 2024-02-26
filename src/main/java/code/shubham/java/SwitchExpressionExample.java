package code.shubham.java;

/*
Support pattern matching.
Pattern matching for switch allows using patterns in case labels, such as type patterns, record patterns, and guarded patterns
 */
public class SwitchExpressionExample {
    sealed interface Shape permits Circle, Rectangle, Triangle {}

    record Circle(double radius) implements Shape {}
    record Rectangle(double length, double width) implements Shape {}
    record Triangle(double base, double height) implements Shape {}

    Shape shape = new Circle(10);

    // Use a switch expression with pattern matching to compute the area of the shape
    double area = switch (shape) {
        case Circle(var radius) -> Math.PI * radius * radius;
        case Rectangle(var length, var width) -> length * width;
        case Triangle(var base, var height) -> base * height / 2;
        default -> throw new IllegalStateException("Unknown shape: " + shape);
    };

    public static void main(String[] args) {
        System.out.println(new SwitchExpressionExample().area);
    }
}
