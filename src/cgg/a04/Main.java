package cgg.a04;

import cgg.Image;
import cgtools.*;

public class Main {
    public static void main(String[] args) {
        final int width = 480;
        final int height = 270;

        Random random = new Random();
        Color color1 = new Color(random.nextDouble(), random.nextDouble(), random.nextDouble(190));
        Color color2 = new Color(random.nextDouble(), random.nextDouble(), random.nextDouble());
        Color color3 = new Color(random.nextDouble(), random.nextDouble(), random.nextDouble(190));

        CameraObscura camera = new CameraObscura(width, height, Math.PI / 3);
        Shape globe1 = new Sphere(Vector.point(-1.0, -0.25, -2.5), 0.7, color1);
        Shape globe2 = new Sphere(Vector.point(0.0, -0.25, -2.5), 0.5, color2);
        Shape globe3 = new Sphere(Vector.point(1.0, -0.25, -2.5), 0.7, color3);

        Group group = new Group();
        group.addShape(globe1);
        group.addShape(globe2);
        group.addShape(globe3);

        // Create new camera and sample pictured shapes
        Image image = new Image(width, height);
        image.sample(8, group, camera);

        // Write the image to disk
        final String filename = "doc/a04-scene.png";
        image.write(filename);
        System.out.println("Wrote image: " + filename);
    }
}
