package code.shubham.trees;

import java.util.Objects;

public class QuadTree<Data> {

    Quad quad;

    QuadTree(int topLeftPositionX, int topLeftPositionY, int bottomRightPositionX, int bottomRightPositionY) {
        this.quad = new Quad(new Quad.Point(topLeftPositionX, topLeftPositionY),
                             new Quad.Point(bottomRightPositionX, bottomRightPositionY));
    }

    static class Quad<Data> {
        Point topLeftPosition;
        Point bottomRightPosition;

        Node node;

        Quad topLeftQuad;
        Quad topRightQuad;
        Quad bottomLeftQuad;
        Quad bottomRightQuad;

        Quad(Point topLeftPosition, Point bottomRightPosition) {
            this.topLeftPosition = topLeftPosition;
            this.bottomRightPosition = bottomRightPosition;
        }

        static class Point {
            int x;
            int y;
            final static transient int UNIT_MINIMUM = 1;
            Point(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }

        static class Node<Data> {
            Point position;
            Data data;
            Node (int positionX, int positionY) {
                this(null, positionX, positionY);
            }
            Node (Data data, int positionX, int positionY) {
                this.data = data;
                this.position = new Point(positionX, positionY);
            }
        }

        void putSafely(Node node) {
            if (Objects.isNull(node)) throw new IllegalArgumentException();
            if (Objects.isNull(node.position)) throw new IllegalArgumentException();
            if (!contains(node.position)) throw new IllegalArgumentException();
            put(node);
        }

        void put(Node node) {
            if (!contains(node.position)) return;
            if (Math.abs(topLeftPosition.x - bottomRightPosition.x) <= Point.UNIT_MINIMUM
             && Math.abs(topLeftPosition.y - bottomRightPosition.y) <= Point.UNIT_MINIMUM) {
                if (Objects.isNull(this.node)) this.node = new Node(node.position.x, node.position.y);
                this.node.data = node.data;
                return;
            }
            if ((bottomRightPosition.x - topLeftPosition.x) / 2 >= node.position.x) {
                if ((topLeftPosition.y - bottomRightPosition.y) / 2 >= node.position.y) {
                    if (bottomLeftQuad == null) {
                        bottomLeftQuad = new Quad(new Point(topLeftPosition.x, (topLeftPosition.y - bottomRightPosition.y) / 2),
                                                  new Point((bottomRightPosition.x - topLeftPosition.x) / 2, bottomRightPosition.y));
                    }
                    bottomLeftQuad.put(node);
                } else {
                    if (topRightQuad == null) {
                        topRightQuad = new Quad(new Point(topLeftPosition.x, topLeftPosition.y),
                                                new Point((bottomRightPosition.x - topLeftPosition.x) / 2,
                                                         (topLeftPosition.y - bottomRightPosition.y) / 2));
                    }
                    topRightQuad.put(node);
                }
            } else {
                if ((topLeftPosition.y - bottomRightPosition.y) / 2 >= node.position.y) {
                    if (bottomRightQuad == null) {
                        bottomRightQuad = new Quad(new Point((bottomRightPosition.x - topLeftPosition.x) / 2,
                                                             (topLeftPosition.y - bottomRightPosition.y) / 2),
                                                   new Point(bottomRightPosition.x, bottomRightPosition.y));
                    }
                    bottomRightQuad.put(node);
                } else {
                    if (topRightQuad == null) {
                        topRightQuad = new Quad(new Point( (bottomRightPosition.x - topLeftPosition.x) / 2, topLeftPosition.y),
                                                new Point(bottomRightPosition.x, (topLeftPosition.y - bottomRightPosition.y) / 2));
                    }
                    topRightQuad.put(node);
                }
            }
        }

        Node search(Point position) {
            if (!contains(position)) return null;
            if (node != null) return node;
            if ((bottomRightPosition.x - topLeftPosition.x) / 2 >= position.x)
                if ((topLeftPosition.y - bottomRightPosition.y) / 2 >= position.y)
                    return bottomLeftQuad == null ? null : bottomLeftQuad.search(position);
                else return topLeftQuad  == null ? null : topLeftQuad.search(position);
            else
                if ((topLeftPosition.y - bottomRightPosition.y) / 2 >= position.y)
                    return bottomRightQuad == null ? null : bottomRightQuad.search(position);
                else return topRightQuad == null ? null : topRightQuad.search(position);
        }

        boolean contains(Point position) {
            return topLeftPosition.x <= position.x && position.x <= bottomRightPosition.x
                && topLeftPosition.y >= position.y && position.y >= bottomRightPosition.y;
        }

    }

    void put(Data data, int x, int y) {
        this.quad.putSafely(new Quad.Node(data, x, y));
    }

    Data search(int x, int y) {
        return (Data) this.quad.search(new Quad.Point(x, y)).data;
    }

    public static void main(String[] args) {
        QuadTree tree = new QuadTree(0, 10, 10, 0);
        tree.put(101, 0, 0);
        System.out.println(tree.search(0, 0));
    }

}
