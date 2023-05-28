package cgg.a03;

import cgg.a05.DiffuseMaterial;
import cgtools.Color;
import cgtools.Sampler;

import static cgtools.Util.shade;
import static cgtools.Vector.*;

import java.util.ArrayList;
import java.util.List;

public class ThreeSpheres implements Sampler {

    private Camera cam;
    private List<Sphere> spheres;

    public ThreeSpheres(double angle) {
        this.cam = new Camera(angle, 1280, 720);
        this.spheres = new ArrayList<>();
        spheres.add(new Sphere(point(-600, 0, -10000), 270, new DiffuseMaterial(new Color(1, 0.4, 0.64))));
        spheres.add(new Sphere(point(0, 0, -10000), 270, new DiffuseMaterial(new Color(0.4, 1, 0.4))));
        spheres.add(new Sphere(point(600, 0, -10000), 270, new DiffuseMaterial(new Color(0.3, 0.65, 1))));
    }

    public int width() {
        return 0;
    }
    public int height() {
        return 0;
    }

    @Override
    public Color getColor(double x, double y) {
        Ray ray = cam.generateRay(x, y);
        // Generate a ray for each sphere and check if it intersects.
        for (Sphere s : spheres) {
            Hit hit = s.intersect(ray);
            if (hit != null) {
                return shade(hit.normal(), hit.material().properties(ray, hit).albedo());
            }
        }
        return new Color(0.05, 0.05, 0.05);
    }
}