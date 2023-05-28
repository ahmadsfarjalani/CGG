package cgg.a04;

import cgg.Image;
import cgg.a03.Hit;
import cgg.a03.Ray;
import cgg.a05.Material;
import cgtools.Direction;
import cgtools.Point;

import static cgtools.Vector.*;

public record Plane(Point anchor, Direction normal, double radius, Material material) implements Shape {

    @Override
    public Hit intersect(Ray ray) {
        double denominator = dotProduct(ray.direction, normal);
        if (denominator == 0) {
            return null;
        }
        double t = dotProduct(subtract(anchor, ray.source), normal) / denominator;
        if (t < ray.tmin || t > ray.tmax) {
            return null;
        }
        Point hit = add(ray.source, multiply(t, ray.direction));
        if (length(subtract(hit, anchor)) > radius) {
            return null;
        }
        double u = hit.x() / material.width() + 0.5;
        double v = hit.z() / material.height() + 0.5;
        return new Hit(t, hit, normal, material, u, v);
    }
}
