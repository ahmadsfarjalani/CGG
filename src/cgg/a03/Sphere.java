package cgg.a03;

import cgg.a04.Shape;
import cgg.a05.Material;
import cgtools.Direction;
import cgtools.Point;

import static cgtools.Vector.*;
import static cgtools.Vector.subtract;

public record Sphere(Point center, double radius, Material material) implements Shape {

    /**
     * Checks if a given ray intersects the sphere. If it does, returns a hit object with the intersection.
     * Only accepts a hit if the ray parameter t is within bounds.
     *
     * @param r the ray to be checked.
     * @return null if the ray doesn't intersect the sphere; a hit object with the first intersection if it does.
     */
    public Hit intersect(Ray r) {
        // Generate a second ray which is offset by the location of the sphere. This is needed for a correct calculation.
        Point source = subtract(r.source, direction(center.x(), center.y(), center.z()));
        Ray r2 = new Ray(source, r.direction, r.tmin, r.tmax);
        // Calculate coefficients a, b and c
        double a = dotProduct(r2.direction, r2.direction);
        double b = 2 * dotProduct(r2.source, r2.direction);
        double c = dotProduct(r2.source, r2.source) - radius * radius;
        double discriminant = b * b - 4 * a * c;

        if (discriminant < 0) {
            return null;
        }
        // Calculate ray parameter t for the first intersection and check if it is within bounds.
        double t0 = (-b - Math.sqrt(discriminant)) / 2 * a;
        double t1 = (-b + Math.sqrt(discriminant)) / 2 * a;

        double t;
        if (r.isValid(t0)) {
            t = t0;
        } else if (r.isValid(t1)) {
            t = t1;
        } else return null;
        // Generate a hit object
        Point hit = add(r.source, multiply(t, r.direction));
        Direction normal = divide(subtract(hit, center), radius);
        double inclination = Math.acos(normal.y());
        double azimuth = Math.PI + Math.atan2(normal.x(), normal.z());
        double u = azimuth / (2 * Math.PI);
        double v = inclination / Math.PI;
        return new Hit(t, hit, normal, material, u, v);
    }
}
