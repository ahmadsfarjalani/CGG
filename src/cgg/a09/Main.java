package cgg.a09;

import cgg.a03.Camera;
import cgg.a03.Sphere;
import cgg.a04.Background;
import cgg.a04.Group;
import cgg.a04.Plane;
import cgg.a04.Shape;
import cgg.a05.BackgroundMaterial;
import cgg.a05.DiffuseMaterial;
import cgg.a06.Cylinder;
import cgg.a07.Transformation;
import cgtools.Color;
import cgtools.ImageTexture;
import cgtools.Matrix;

import java.util.ArrayList;
import java.util.List;

import static cgg.a07.Main.createImage;
import static cgtools.ImageTexture.getFilepath;
import static cgtools.Vector.direction;
import static cgtools.Vector.point;

public class Main {

    private static final List<Shape> shapes = new ArrayList<>();

    private static final Shape background = new Background(new BackgroundMaterial(new ImageTexture(getFilepath("background.png"))));

    private static final Shape ground = new Plane(
            point(0, 0, 0), 
            direction(0, 1, 0),
            Double.POSITIVE_INFINITY, 
            new DiffuseMaterial(new ImageTexture(getFilepath("boden123.png")))
    );

    private static final Matrix matrix = Matrix.translation(0, 1, 10);
    private static final Matrix transformation = Matrix.multiply(Matrix.translation(0, 2000, 0), Matrix.rotation(1, 0, 0, -90));

    public static void main(String[] args) {
        shapes.add(ground);
        shapes.add(background);
        shapes.add(new Group(
                new Transformation(Matrix.rotation(0, 1, 0, -80)),
                new Sphere(
                    point(0, 1, 0), 
                    1, 
                    new DiffuseMaterial(new ImageTexture(getFilepath("YEPP.png")))
                ),
                new Sphere(
                    point(2, 1, 2), 
                    1, 
                    new DiffuseMaterial(new ImageTexture(getFilepath("patrick.png")))
                ),
                new Sphere(
                    point(-3, 2, -3), 
                    2, 
                    new DiffuseMaterial(new ImageTexture(getFilepath("spongebob.png")))
                ),
                new Sphere(
                    point(-2, 4 ,4),
                    4,
                    new DiffuseMaterial(new ImageTexture(getFilepath("krabs.png")))
                ),
                new Sphere(
                    point(-6, 3, -8),
                    3,
                    new DiffuseMaterial(new ImageTexture(getFilepath("taddel.png")))
                )
        ));

        Group scene = new Group(shapes);
        Camera camera = new Camera(Math.PI / 2, 1280, 720, matrix);
        createImage(camera, scene, "a09.png");
    }
}
