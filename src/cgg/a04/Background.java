package cgg.a04;

import cgtools.*;

public class Background implements Shape {
    private Color color;

    public Background(Color color) {
        this.color = color;
    }

    public Hit intersect(Ray r) {
        // Direction normal = Vector.normalize(r.getDirection());
        // Hit hitPoint = new Hit(Double.POSITIVE_INFINITY, r.pointAt(Double.POSITIVE_INFINITY), normal, color);
        Hit hitPoint = new Hit(r.getTmax(), r.pointAt(r.getTmax()), new Direction(1, 0, 0), color);
        return hitPoint;
    }
}