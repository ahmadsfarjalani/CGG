package cgg.a05;

import cgtools.*;

public class Raytracer implements Sampler {
    CameraObscura camera;
    Group group;
    int recursionDepth;

    public Raytracer(CameraObscura camera, Group group, int recursionDepth) {
        this.camera = camera;
        this.group = group;
        this.recursionDepth = recursionDepth;
    }

    public Color getColor(double x, double y) {
        Ray r = camera.generateRay(x, y);
        return calculateRadiance(group, r, recursionDepth);
    }

    public Color calculateRadiance(Shape scene, Ray ray, int depth) {
        if(depth == 0) {
            return Vector.black;
        }

        Hit hit = scene.intersect(ray);
        Material material = hit.getHitPointMaterial();
        Ray scatteredRay = material.scatteredRay(ray, hit);
        Color emission = material.emission();

        if(scatteredRay != null) {
            Color color = Vector.multiply(material.albedo(), calculateRadiance(scene, scatteredRay, depth - 1));
            color = Vector.add(material.emission(), color);

            return color;
        }
        else {
            return emission;
        }
    }

    public static Color shade(Direction normVec, Object hitPointColor) {
        return null;
    }
}
