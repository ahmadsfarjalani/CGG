package cgg.a11;

import java.util.concurrent.ExecutionException;

import cgg.Image;
import cgtools.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        final int width = 480;
        final int height = 270;

        Group scene = new Group(new Transformation(Matrix.identity));
        Plane ground = new Plane(Vector.point(0.0, 0.0, 0.0), Vector.direction(0.0, 1.0, 0.0), new GlassMaterial(new ConstantColor(Vector.black), 2), Double.POSITIVE_INFINITY);
        Background background = new Background(new BackgroundMaterial(new Texture("img/sky.png", Matrix.translation(0, 0, 0))));
        Sphere sphere1 = new Sphere(Vector.point(0, 0.3, -0.5), 0.3, new DiffusingMaterial(new ConstantColor(Vector.white)));
        Sphere sphere2 = new Sphere(Vector.point(0, 0.5, -3), 0.5, new DiffusingMaterial(new ConstantColor(Vector.white)));

        scene.addShape(background);
        scene.addShape(ground);
        scene.addShape(sphere1);
        scene.addShape(sphere2);


        World world = new World(scene);
        world.addLightSource(new PointLightSource(Vector.point(-1, 1, -1), Vector.blue, 1));
        world.addLightSource(new PointLightSource(Vector.point(1, 1, -1), Vector.red, 1));
        world.addLightSource(new PointLightSource(Vector.point(-1, 1, -3), Vector.green, 1));
        world.addLightSource(new PointLightSource(Vector.point(1, 1, -3), new Color(1, 0, 1), 1));
        world.addLightSource(new DirectionLightSource(Vector.direction(-2,-1,-2), Vector.white, 0.25));

        CameraObscura camera = new CameraObscura(width, height, Math.PI / 2, Vector.point(0, 1, 2));

        // CameraObscura camera = new CameraObscura(width, height, Math.PI / 2, Vector.point(0.3, 1, 4), Matrix.rotation(Vector.direction(1, 0, 0), -50));
        
        // Create new camera and sample pictured shapes
        Image shapes = new Image(width, height);
        shapes.sample(150, scene, camera, new RayTracing(camera, world, 5), 50, 8);
            
        // // Write the images to disk
        // final String filename = "doc/a11-1.png";
        // shapes.write(filename);
        // System.out.println("Wrote image: " + filename);


        // Write the images to disk
        final String filename = "doc/a11.png";
        shapes.write(filename);
        System.out.println("Wrote image: " + filename);
    }
}