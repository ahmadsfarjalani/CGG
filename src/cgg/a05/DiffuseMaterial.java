package cgg.a05;

import cgg.a01.ConstantColor;
import cgg.a03.Hit;
import cgg.a03.Ray;
import cgtools.Color;
import cgtools.Sampler;

import static cgtools.Random.random;
import static cgtools.Vector.*;

public class DiffuseMaterial implements Material {

    public final Sampler albedo;
    public final Color emission = new Color(0, 0, 0);

    private final double EPSILON = 0.0001;

    public DiffuseMaterial(Color color) {
        this.albedo = new ConstantColor(color);
    }

    public DiffuseMaterial(Sampler texture) {
        this.albedo = texture;
    }

    @Override
    public int width() {
        return albedo.width();
    }

    @Override
    public int height() {
        return albedo.height();
    }

    @Override
    public Properties properties(Ray incomingRay, Hit hit) {
        double x = random() * 2 - 1;
        double y = random() * 2 - 1;
        double z = random() * 2 - 1;
        while (x * x + y * y + z * z > 1) {
            x = random() * 2 - 1;
            y = random() * 2 - 1;
            z = random() * 2 - 1;
        }
        Ray scatteredRay = new Ray(hit.position(), normalize(add(hit.normal(), direction(x, y, z))), EPSILON, Double.POSITIVE_INFINITY);
        return new Properties(scatteredRay, emission, albedo.getColor(hit.u(), hit.v()));
    }

}
