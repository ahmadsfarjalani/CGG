package cgg.a11;

import java.util.concurrent.ExecutionException;

import cgg.Image;
import cgtools.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        final int width = 480;
        final int height = 270;

        Group scene = new Group(new Transformation(Matrix.identity));
        
        Texture groundTexture = new Texture("img/himmel.png", Matrix.translation(0, 0, 0));
        DiffusingMaterial groundMaterial = new DiffusingMaterial(groundTexture);
        Plane ground = new Plane(Vector.point(0.0, 0.0, 0.0), Vector.direction(0.0, 1.0, 0.0), groundMaterial, Double.POSITIVE_INFINITY);

        Background background = new Background(new BackgroundMaterial(new Texture("img/himmel.png", Matrix.translation(0, 0, 0))));
        Sphere sphere1 = new Sphere(Vector.point(0, 0.4, -0.5), 0.5, new GlassMaterial(new ConstantColor(Vector.white),0.01));

        
        



        scene.addShape(background);
        scene.addShape(ground);
        scene.addShape(sphere1);


        World world = new World(scene);
        world.addLightSource(new PointLightSource(Vector.point(-1, 0.5, -1), Vector.blue, 3));
        world.addLightSource(new PointLightSource(Vector.point(1, 0.5, -1), Vector.red, 3));
        world.addLightSource(new PointLightSource(Vector.point(-1, 0.5, -3), Vector.green, 3));
        world.addLightSource(new PointLightSource(Vector.point(1, 0.5, -3), new Color(1, 0, 1), 3));
        world.addLightSource(new DirectionLightSource(Vector.direction(-2,-1,-2), Vector.white, 0.25));

        CameraObscura camera = new CameraObscura(width, height, Math.PI / 2, Vector.point(0, 1, 4));

        // Create new camera and sample pictured shapes
        Image shapes = new Image(width, height);
        shapes.sample(200, scene, camera, new RayTracing(camera, world, 5), 50, 8);

        // Write the images to disk
        final String filename = "doc/a11.png";
        shapes.write(filename);
        System.out.println("Wrote image: " + filename);
    }
}
