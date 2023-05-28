package cgg.a04;

import cgg.a03.Hit;
import cgg.a03.Ray;
import cgg.a07.Transformation;
import cgtools.Matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Group implements Shape {

    private final List<Shape> shapes = new ArrayList<>();
    private final Transformation transformation;

    public Group(Transformation transformation, Shape... shapes) {
        this.transformation = transformation;
        this.shapes.addAll(Arrays.asList(shapes));
    }

    public Group(Shape... shapes) {
        this.transformation = new Transformation(Matrix.identity());
        this.shapes.addAll(Arrays.asList(shapes));
    }

    public Group(Transformation transformation, List<Shape> shapes) {
        this.transformation = transformation;
        this.shapes.addAll(shapes);
    }

    public Group(List<Shape> shapes) {
        this.transformation = new Transformation(Matrix.identity());
        this.shapes.addAll(shapes);
    }

    @Override
    public Hit intersect(Ray ray) {
        Ray r2 = transformation.transform(ray);
        Hit hit = null;
        for (Shape s: shapes) {
            Hit h = s.intersect(r2);
            if (h != null) {
                if (hit == null) {
                    hit = h;
                } else {
                    if (h.t() < hit.t()) {
                        hit = h;
                    }
                }
            }
        }
        return transformation.transform(hit);
    }
}
