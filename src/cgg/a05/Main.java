package cgg.a05;

import cgg.Image;
import cgg.a03.Camera;
import cgg.a03.Hit;
import cgg.a03.Ray;
import cgg.a03.Sphere;
import cgg.a04.*;
import cgtools.Color;

import java.util.ArrayList;
import java.util.List;

import static cgtools.Random.random;
import static cgtools.Vector.direction;
import static cgtools.Vector.point;

public class Main {

    private static final Camera camera = new Camera(Math.PI / 3, 1280, 720);

    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>();

        Shape background = new Background(new BackgroundMaterial(new Color(1, 1, 1)));
        Shape ground = new Plane(point(0.0, -0.5, 0.0), direction(0, 1, 0), Double.POSITIVE_INFINITY,
                new DiffuseMaterial(new Color(0.6, 0.6, 0.6)));
        shapes.add(background);
        shapes.add(ground);

        for (int i = 0; i < 15; i++) {
            Ray r = camera.generateRay(random() * 1280, random() * 290 + 360);
            Hit h = ground.intersect(r);
            double radius = random() * 0.25 + 0.05;
            double y = -0.5 + radius;
            Shape globe = new Sphere(point(h.position().x(), y, h.position().z()), radius,
                    new DiffuseMaterial(new Color(random(), random(), random())));
            shapes.add(globe);
        }

        Group scene = new Group(shapes);
        Image image = new Image();

        Raytracer raytracer = new Raytracer(scene, camera, image);
        raytracer.raytrace();
        image.write(Image.getFilepath("a05-diffuse-spheres.png"));
    }
}
