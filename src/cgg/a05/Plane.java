package cgg.a05;

import cgtools.*;

public class Plane implements Shape {
    public final Point point;
    public final Direction normal;
    public final Material material;
    public final double rPara;

    public Plane(Point point, Direction normal, Material material, double rPara) {
        this.point = point;
        this.normal = normal;
        this.material = material;
        this.rPara = rPara;
    }

    public Hit intersect(Ray r) {
        double dn = Vector.dotProduct(r.getDirection(), normal);

        double t = Vector.dotProduct(Vector.subtract(point, r.getOrigin()), normal) / dn;
        Point cuttingPoint = r.pointAt(t);

        if(dn == 0 || !r.isValid(t) || Vector.length(Vector.subtract(cuttingPoint, point)) > rPara) {
            return null;
        }

        Hit hitPoint = new Hit(t, cuttingPoint, normal, material);
        return hitPoint;
    }
}