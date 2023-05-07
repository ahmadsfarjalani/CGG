package cgg.a03;

import java.util.ArrayList;

import cgtools.*;

public class RayTracing implements Sampler {
    CameraObscura camera;
    ArrayList<Sphere> spheres;

    
    Random random = new Random();

    public RayTracing(int width, int height, CameraObscura camera, int spheresAmount) {
        this.camera = camera;
        

        spheres = new ArrayList<Sphere>();
        for (int i = 0; i < spheresAmount; i++) {
            double randomX = random.nextDouble(-200, 200);
            double randomY = random.nextDouble(-200, 200);
            double randomZ = random.nextDouble(-220, -100);

            double randomRadius = random.nextDouble(15, 25);

            Color color = new Color(random.nextDouble(), random.nextDouble(), random.nextDouble());
            Sphere randomSphere = new Sphere(new Point(randomX, randomY, randomZ), randomRadius, color);
            spheres.add(randomSphere);
        }

      
    }

    public Color getColor(double x, double y) {
        Ray r = camera.generateRay(x, y);
        Hit closestHit = null;
        for(Sphere sphere: spheres) {
            Hit hit = sphere.intersect(r);
            if(hit != null) {
                if((closestHit == null) || (hit.getRayParameterT() < closestHit.getRayParameterT())) {
                    closestHit = hit;
                }
            }
        }
        if(closestHit != null) {
            return shade(closestHit.getNormVec(), closestHit.getHitPointColor());
        }
        return Vector.black;
    }

    public static Color shade(Direction normal, Color color) {
        Direction lightingDirection = Vector.normalize(Vector.direction(1, 1, 0.5));
        Color ambient = Vector.multiply(0.1, color);
        Color diffuse = Vector.multiply(0.9 * Math.max(0, Vector.dotProduct(lightingDirection, normal)), color);
        return Vector.add(ambient, diffuse);
    }
}