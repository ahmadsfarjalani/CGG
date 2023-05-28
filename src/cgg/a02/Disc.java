package cgg.a02;

import cgtools.Color;

public class Disc {

    private int x;
    private int y;
    private int radius;
    private Color color;

    public int getRadius() {
        return radius;
    }

    public Color getColor() {
        return color;
    }

    public Disc(int x, int y, int radius, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }

    public boolean isPointInDisc(double x, double y) {
        // offset x and y so that the center of the disc has the coordinates (0, 0).
        x = x - this.x;
        y = y - this.y;
        // determine if the pixel is inside the circle.
        double result = x * x + y * y - Math.pow(this.radius, 2);
        return result < 0;
    }
}
