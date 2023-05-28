package cgg.a04;

import cgg.a03.Hit;
import cgg.a03.Ray;
import cgg.a05.Material;
import cgtools.Direction;
import cgtools.Point;

import static cgtools.Vector.*;

public record Background(Material material) implements Shape {

    @Override
    public Hit intersect(Ray ray) {
        if (ray.tmax != Double.POSITIVE_INFINITY) {
            return null;
        }
        Point hit = add(ray.source, multiply(ray.tmax, ray.direction));
        Direction normal = ray.direction;
        double inclination = Math.acos(normal.y());
        double azimuth = Math.PI + Math.atan2(normal.x(), normal.z());
        double u = azimuth / (2 * Math.PI);
        double v = inclination / Math.PI;
        return new Hit(ray.tmax, hit, negate(ray.direction), material, u, v);
    }
}
