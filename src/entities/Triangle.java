package entities;

public class Triangle {
    private Point a;
    private Point b;
    private Point c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Point getA() {
        return a;
    }
    public void setA(Point a) {
        this.a = a;
    }
    public Point getB() {
        return b;
    }
    public void setB(Point b) {
        this.b = b;
    }
    public Point getC() {
        return c;
    }
    public void setC(Point c) {
        this.c = c;
    }

    public boolean isTriangle() {
        double ab = a.distance(b);
        double bc = b.distance(c);
        double ca = c.distance(a);
        return (ab + bc > ca) && (bc + ca > ab) && (ab + ca > bc);
    }
    public boolean isInTriangle(Point p) {
        Triangle pbc = new Triangle(p, b, c);
        Triangle apc = new Triangle(a, p, c);
        Triangle abp = new Triangle(a, b, p);
        return pbc.getArea() + apc.getArea() + abp.getArea() == this.getArea();
    }

    public double getPerimeter() {
        double ab = a.distance(b);
        double bc = b.distance(c);
        double ca = c.distance(a);
        return ab + bc + ca;
    }
    public double getArea() {
        double ab = a.distance(b);
        double bc = b.distance(c);
        double ca = c.distance(a);
        double p = (ab + bc + ca) / 2;
        return Math.sqrt(p * (p - ab) * (p - bc) * (p - ca));
    }

}
