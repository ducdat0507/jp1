package entities;

public class Point {
    private double x;
    private double y;

    
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double[] getXY() {
        return new double[] {x, y};
    }
    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point that) {
        return Math.sqrt(Math.pow(this.x - that.x, 2) + Math.pow(this.y - that.y, 2));
    }

    public Point() {
        this.x = 0;
        this.y = 0;
    }
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
