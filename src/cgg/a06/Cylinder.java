package cgg.a06;

import cgg.a03.Hit;
import cgg.a03.Ray;
import cgg.a04.Plane;
import cgg.a04.Shape;
import cgg.a05.Material;
import cgtools.Direction;
import cgtools.Point;

import static cgtools.Vector.*;

public class Cylinder implements Shape {

    public final Direction orientation = direction(0, 1, 0);

    public final Point anchor;
    public final double radius;
    public final double height;
    public final Plane bottom;
    public final Plane top;
    public final Material material;

    public Cylinder(Point anchor, double radius, double height, Material material) {
        this.anchor = anchor;
        this.radius = radius;
        this.height = height;
        this.bottom = new Plane(anchor, orientation, radius, material);
        this.top = new Plane(add(anchor, direction(0, height, 0)), orientation, radius, material);
        this.material = material;
    }

    /*
    Sources:
    https://www.cl.cam.ac.uk/teaching/1999/AGraphHCI/SMAG/node2.html#SECTION00023200000000000000
    https://stackoverflow.com/questions/36266357/how-can-i-compute-normal-on-the-surface-of-a-cylinder
     */
    @Override
    public Hit intersect(Ray r) {
        // Offset ray and ignore y coords.
        Direction offset = subtract(r.source, bottom.anchor());
        Point source = point(offset.x(), 0, offset.z());
        Direction direction = direction(r.direction.x(), 0, r.direction.z());
        // calculate coefficients a, b, and c.
        double a = dotProduct(direction, direction);
        double b = 2 * dotProduct(source, direction);
        double c = dotProduct(source, source) - Math.pow(radius, 2);
        // Calculate discriminant.
        double discriminant = b * b - 4 * a * c;
        if (discriminant < 0) {
            return null;
        }
        // Calculate ray parameters t for both intersections.
        double t1 = (-b - Math.sqrt(discriminant)) / (2 * a);
        double t2 = (-b + Math.sqrt(discriminant)) / (2 * a);
        // Calculate hit points and generate hit objects.
        Point p1 = r.pointAt(t1);
        Point p2 = r.pointAt(t2);
        Hit h1 = null;
        Hit h2 = null;
        // Check whether hits are valid or not.
        if ((r.isValid(t1) && isValid(p1.y()))) {
            Direction normal = calculateNormal(p1);
            double inc = Math.cos(normal.y());
            double azi = Math.PI + Math.atan2(normal.x(), normal.z());
            double u = azi / (2 * Math.PI);
            double v = inc / Math.PI;
            h1 = new Hit(t1, p1, normal, material, u, v);
        }
        if (r.isValid(t2) && isValid(p2.y())) {
            Direction normal = calculateNormal(p2);
            double inc = Math.cos(normal.y());
            double azi = Math.PI + Math.atan2(normal.x(), normal.z());
            double u = azi / (2 * Math.PI);
            double v = inc / Math.PI;
            h2 = new Hit(t2, p2, normal, material, u, v);
        }
        // Intersect ray with top and bottom caps.
        Hit h3 = bottom.intersect(r);
        Hit h4 = top.intersect(r);
        // Return the hit object which is closest to the camera.
        return Hit.min(h1, h2, h3, h4);
    }

    private boolean isValid(double y) {
        return (y >= bottom.anchor().y() && y <= top.anchor().y());
    }

    private Direction calculateNormal(Point p) {
        return normalize(divide(subtract(p, this.bottom.anchor()), radius));
    }
}
