package cgg.a08;

import cgg.a03.Sphere;
import cgg.a04.Background;
import cgg.a04.Group;
import cgg.a04.Plane;
import cgg.a04.Shape;
import cgg.a05.BackgroundMaterial;
import cgg.a05.DiffuseMaterial;
import cgtools.Color;
import cgtools.Matrix;

import java.util.ArrayList;
import java.util.List;

import static cgg.a07.Main.createImage;
import static cgtools.Vector.direction;
import static cgtools.Vector.point;

public class Main {

    private static final List<Shape> shapes = new ArrayList<>();

    private static final Shape background = new Background(new BackgroundMaterial(new Color(1, 1, 1)));
    private static final Shape ground = new Plane(point(0, 0, 0), direction(0, 1, 0),
            Double.POSITIVE_INFINITY, new DiffuseMaterial(new Color(0.5, 0.5, 0.5)));

    public static void main(String[] args) {
        Matrix transformation = Matrix.multiply(Matrix.translation(0, 200, 0), Matrix.rotation(1, 0, 0, -90));
        shapes.add(ground);
        shapes.add(background);
        for (double x = -100; x <= 100; x += 10) {
            for (double z = -50; z <= 50; z += 10) {
                shapes.add(new Sphere(point(x, 1, z), 2,
                        new DiffuseMaterial(new Color(1, 0.5, (x + z + 150) / 300))));
            }
        }
        Group scene = new Group(shapes);
        createImage(transformation, scene, "a09-benchmark-sccccccene.png");
        
    }
}