package cgg.a04;

import cgg.Image;
import cgg.a03.Camera;
import cgg.a03.Hit;
import cgg.a03.Ray;
import cgg.a05.Properties;
import cgtools.Color;
import cgtools.Sampler;

import static cgtools.Color.add;
import static cgtools.Color.multiply;
import static cgtools.Util.shade;

public record Raytracer(Group scene, Camera cam, Image image) implements Sampler {

    public void raytrace() {
        image.sample(this);
    }

    @Override
    public int width() {
        return 0;
    }

    @Override
    public int height() {
        return 0;
    }

    @Override
    public Color getColor(double x, double y) {
        Ray ray = cam.generateRay(x, y);
        return calculateRadiance(scene, ray, 5);
    }

    public Color calculateRadiance(Shape scene, Ray ray, int depth) {
        // Check for maximum recursion depth
        if (depth == 0) {
            return new Color(0, 0, 0);
        }
        // Intersect ray with scene
        Hit hit = scene.intersect(ray);
        // Query material at hit point
        Properties properties = hit.material().properties(ray, hit);
        if (properties.scatteredRay() == null) {
            // Absorbed, just emission
            return properties.emission();
        } else {
            // Combine emission and reflection
            return add(properties.emission(), multiply(properties.albedo(),
                    calculateRadiance(scene, properties.scatteredRay(), depth - 1)));
        }
    }
}
