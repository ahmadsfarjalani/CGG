package cgg.a04;

import cgg.Image;
import cgg.a03.Camera;
import cgg.a03.Hit;
import cgg.a03.Ray;
import cgg.a03.Sphere;
import cgg.a05.BackgroundMaterial;
import cgg.a05.DiffuseMaterial;
import cgtools.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static cgtools.Random.random;
import static cgtools.Vector.*;

public class Main {

    private static final Camera camera = new Camera(Math.PI / 3, 1280, 720);

    public static void main(String[] args) {
        generateImage1();
        generateImage2();
    }

    private static void generateImage1() {
        Shape background = new Background(new BackgroundMaterial(new Color(0, 0, 0)));
        Shape ground = new Plane(point(0.0, -0.5, 0.0), direction(0, 1, 0), Double.POSITIVE_INFINITY, new DiffuseMaterial(new Color(1, 0.6, 1)));
        Shape globe1 = new Sphere(point(-1.0, -0.25, -3.5), 0.7, new DiffuseMaterial(new Color(1, 0.15, 0.15)));
        Shape globe2 = new Sphere(point(0.0, -0.25, -3.5), 0.5, new DiffuseMaterial(new Color(0.4, 1, 0.4)));
        Shape globe3 = new Sphere(point(1.0, -0.25, -3.5), 0.7, new DiffuseMaterial(new Color(0.3, 0.65, 1)));
        List<Shape> shapes = Arrays.asList(background, ground, globe1, globe2, globe3);

        Group scene = new Group(shapes);
        Image image = new Image();

        Raytracer raytracer = new Raytracer(scene, camera, image);
        raytracer.raytrace();
        image.write(getFilepath("a04-3-spheres.png"));
    }

    private static void generateImage2() {
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Background(new BackgroundMaterial(new Color(0, 0, 0))));
        shapes.add(new Sphere(point(-8, -27, -30), 27, new DiffuseMaterial(new Color(0.4, 0.05, 0.0))));
        shapes.add(stars());
        shapes.add(craters());

        Image image = new Image();
        Group scene = new Group(shapes);
        Raytracer raytracer = new Raytracer(scene, camera, image);
        raytracer.raytrace();
        image.write(getFilepath("a04-scene.png"));
    }

    private static Group stars() {
        List<Shape> stars = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            double x = (random() - 0.5) * 110;
            double y = (random() - 0.5) * 60;
            double z = -(random() + 9) * 10;
            double radius = random() / 4;
            stars.add(new Sphere(point(x, y, z), radius, new DiffuseMaterial(new Color(1, 1, 1))));
        }
        return new Group(stars);
    }

    private static Group craters() {
        List<Shape> craters = new ArrayList<>();
        craters.add(crater(70, 450));
        craters.add(crater(150, 400));
        craters.add(crater(350, 400));
        craters.add(crater(300, 525));
        craters.add(crater(220, 440));
        craters.add(crater(60, 650));
        craters.add(crater(187, 582));
        craters.add(crater(400, 625));
        craters.add(crater(600, 675));
        craters.add(crater(640, 550));
        craters.add(crater(750, 515));
        craters.add(crater(999, 675));
        craters.add(crater(475, 430));
        craters.add(crater(220, 719));
        craters.add(crater(520, 550));
        craters.add(crater(870, 719));
        craters.add(crater(621, 407));
        craters.add(crater(926, 592));
        return new Group(craters);
    }

    private static Plane crater(int x, int y) {
        Sphere planet = new Sphere(point(-8, -27, -30), 27, new DiffuseMaterial(new Color(0.4, 0.05, 0.0)));
        Ray r = camera.generateRay(x, y);
        Hit h = planet.intersect(r);
        return new Plane(add(direction(0, 0.0001, 0), h.position()), h.normal(), random() * 0.9 + 0.2, new DiffuseMaterial(new Color(0.2, 0.025, 0)));
    }

    private static String getFilepath(String filename) {
        String projectDir = System.getProperty("user.dir");
        return projectDir + "/doc/" + filename;
    }

    private static Group starTemplate() {
        List<Shape> stars = new ArrayList<>();
        stars.add(new Sphere(point(-55, 30, -100), 0.4, new DiffuseMaterial(new Color(1, 1, 0))));
        stars.add(new Sphere(point(55, 30, -100), 0.4, new DiffuseMaterial(new Color(1, 1, 0))));
        stars.add(new Sphere(point(-55, -30, -100), 0.4, new DiffuseMaterial(new Color(1, 1, 0))));
        stars.add(new Sphere(point(55, -30, -100), 0.4, new DiffuseMaterial(new Color(1, 1, 0))));
        stars.add(new Sphere(point(-10, 0, -100), 0.1, new DiffuseMaterial(new Color(1, 1, 0))));
        stars.add(new Sphere(point(-5, 0, -100), 0.2, new DiffuseMaterial(new Color(1, 1, 0))));
        stars.add(new Sphere(point(0, 0, -100), 0.3, new DiffuseMaterial(new Color(1, 1, 0))));
        stars.add(new Sphere(point(5, 0, -100), 0.4, new DiffuseMaterial(new Color(1, 1, 0))));
        stars.add(new Sphere(point(10, 0, -100), 0.5, new DiffuseMaterial(new Color(1, 1, 0))));
        return new Group(stars);
    }
}

