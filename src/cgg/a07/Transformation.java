package cgg.a07;

import cgg.a03.Hit;
import cgg.a03.Ray;
import cgtools.Matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Transformation {

    private final List<Matrix> matrices = new ArrayList<>();

    public Transformation(Matrix... matrices) {
        this.matrices.addAll(Arrays.asList(matrices));
    }

    public Ray transform(Ray r) {
        if (r == null) {
            return null;
        }
        Ray ray = r;
        for (Matrix m : matrices) {
            ray = ray.transform(Matrix.invert(m));
        }
        return ray;
    }

    public Hit transform(Hit h) {
        if (h == null) {
            return null;
        }
        Hit hit = h;
        for (Matrix m : matrices) {
            hit = hit.transform(m, Matrix.transpose(Matrix.invert(m)));
        }
        return hit;
    }
}
