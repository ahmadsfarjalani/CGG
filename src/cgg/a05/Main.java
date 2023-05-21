package cgg.a05;

import cgg.Image;
import cgtools.*;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        final int width = 1280;
        final int height = 720;

        CameraObscura camera = new CameraObscura(width, height, Math.PI / 2.0);
        Background background = new Background(new BackgroundMaterial(new Color(0.5, 0.7, 1.0))); // Hellblauer Hintergrund
        Plane ground = new Plane(Vector.point(0.0, -0.5, 0.0), Vector.direction(0, 1, 0), new DiffusingMaterial(new Color(1.0, 1.0, 1.0)), Double.POSITIVE_INFINITY);

        Group group = new Group();
        group.addShape(background);
        group.addShape(ground);

        double radiusIncrement = 0.1;
        double maxRadius = 0.6; 

        Random random = new Random();

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 6; i++) {
                double x = random.nextDouble() * 5 - 2.5;
                double y = -0.5 + random.nextDouble() * 2; // Zufällige y-Position, von "im Boden" bis "darüber" // test ettewttewtt
                double z = random.nextDouble() * -4 - 1;
                double radius = maxRadius - i * radiusIncrement;

                double t = i / 5.0;
                Color color = smoothColor(t, 1.0 - t, 0.5);
                if (j % 2 == 0) {
                    color = new Color(0.3, 0.6, 0.3);
                } else {
                    color = new Color(0.3, 0.3, 0.6);
                }
                Shape sphere = new Sphere(Vector.point(x, y, z), radius, new DiffusingMaterial(color));
                group.addShape(sphere);
            }
        }

        Raytracer raytracer = new Raytracer(camera, group, 100);

        Image shapes = new Image(width, height);
        shapes.sample(100, group, camera, raytracer, 250);

        final String filename = "doc/a05-diffuse-spheres.png"; 
        shapes.write(filename);
        System.out.println("Wrote image: " + filename);
    }

    private static Color smoothColor(double r, double g, double b) {
        double smoothness = 0.1;
        return new Color(smoothStep(r, smoothness), smoothStep(g, smoothness), smoothStep(b, smoothness));
    }

    private static double smoothStep(double x, double smoothness) {
        double t = clamp((x - smoothness) / (1.0 - 2.0 * smoothness), 0.0, 1.0);
        return t * t * (3.0 - 2.0 * t);
    }

    private static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(value, max));
    }
}
