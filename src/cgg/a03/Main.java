package cgg.a03;

import java.util.ArrayList;

import cgg.Image;
import cgtools.Color;
import cgtools.Point;

public class Main {
    public static void main(String[] args) {
        final int width = 1280;
        final int height = 720;
        Image image = new Image(width, height);

        CameraObscura camera = new CameraObscura (width, height, Math.PI / 2, new Point(0, 0, 0));
        ArrayList<Sphere> spheres = new ArrayList<>();

        // Erstellen der Sphären, um eine "U"-Form zu bilden
        int numSpheres = 10;
        double sphereRadius = 2;
        double distance = 25;
        double yOffset = 10;

        for (int i = 0; i < numSpheres; i++) {
            double xPosition = -distance / 2 + (distance / (numSpheres - 1)) *i;
            double yPosition;

            Color sphereColor = new Color(200/255.0, 200/255.0, 200/255.0);// Ändern Sie die Werte für die gewünschte Farbe der Sphären

            if (i == 0 || i == numSpheres - 1) {
                yPosition = yOffset;
                for (int j = 0; j < numSpheres - 1; j++) {
                    spheres.add(new Sphere(new Point(xPosition, yPosition - j * sphereRadius * 2, -40), sphereRadius, sphereColor));
                }
            } else {
                yPosition = -distance / 2;
            }

            spheres.add(new Sphere(new Point(xPosition, yPosition, -40), sphereRadius, sphereColor));
        }

        image.sample(100, spheres, camera);

        // Write the image to disk.
        final String filename = "doc/a03-spheres.png";
        image.write(filename);
        System.out.println("Wrote image: " + filename);
    }
}