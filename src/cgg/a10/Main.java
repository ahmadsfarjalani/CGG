package cgg.a10;

import cgg.Image;
import cgg.a03.Camera;
import cgg.a03.Hit;
import cgg.a03.Ray;
import cgg.a03.Sphere;
import cgg.a04.*;
import cgg.a05.BackgroundMaterial;
import cgg.a05.DiffuseMaterial;
import cgg.a05.Material;
import cgg.a06.ReflectiveMaterial;
import cgg.a06.TransparentMaterial;
import cgtools.Color;
import cgtools.Point;

import java.util.ArrayList;
import java.util.List;

import static cgtools.Vector.direction;
import static cgtools.Vector.point;

public class Main {

    private static final Camera camera = new Camera(Math.PI / 3, 1280, 720);

    private static Shape background = new Background(new BackgroundMaterial(new Color(1, 1, 1)));
    private static Shape ground = new Plane(point(0.0, -0.5, 0.0), direction(0, 1, 0),
            Double.POSITIVE_INFINITY, new DiffuseMaterial(new Color(0.6, 0.8, 1.0)));

    public static void main(String[] args) {
        createImage();
    }

    private static void createImage() {
        List<Shape> shapes = new ArrayList<>();

        shapes.add(ground);
        shapes.add(background);
        shapes.add(sphere(640, 650, 0.3, transparent(0.9, 0.9, 0.9))); // Transparent
        shapes.add(sphere(470, 500, 0.5, diffuse(0.8, 0.4, 0.6))); // Pastell-Pink
        shapes.add(sphere(810, 500, 0.5, reflective(0.25))); // Reflective
        shapes.add(sphere(300, 550, 0.3, reflective(0))); // Reflective
        shapes.add(sphere(980, 550, 0.3, diffuse(0.4, 0.8, 0.6))); // Pastell-Grün
        shapes.add(sphere(130, 600, 0.2, diffuse(0.8, 0.8, 0.2))); // Pastell-Gelb
        shapes.add(sphere(1150, 600, 0.2, transparent(1, 0.9, 0.6))); // Transparent
        shapes.add(spehre(1150, 600 , 0,2, transparent (1 , 0.4 , 0.6 )));
        Group scene = new Group(shapes);
        createImage(scene, "a10.png");
    }

    private static void createImage(Group scene, String filename) {
        Image image = new Image();
        Raytracer raytracer = new Raytracer(scene, camera, image);
        raytracer.raytrace();
        image.write(Image.getFilepath(filename));
    }

     private static Sphere sphere(double x, double y, double radius, Material material) {
        Ray ray = camera.generateRay(x, y);
        Hit hit = ground.intersect(ray);
        Point center = point(hit.position().x(), -0.5 + radius, hit.position().z());
        return new Sphere(center, radius, material);
        
    }

    private static Material diffuse(double r, double g, double b) {
        return new DiffuseMaterial(new Color(r, g, b));
    }

    private static Material reflective(double diffusionFactor) {
        return new ReflectiveMaterial(new Color(0.9, 0.9, 0.9), diffusionFactor);
    }

    private static Material transparent(double r, double g, double b) {
        return new TransparentMaterial(new Color(r, g, b), 1, 1.5);
    }
}
