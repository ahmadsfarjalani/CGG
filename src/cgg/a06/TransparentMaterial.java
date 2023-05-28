package cgg.a06;

import cgg.a01.ConstantColor;
import cgg.a03.Hit;
import cgg.a03.Ray;
import cgg.a05.Material;
import cgg.a05.Properties;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Sampler;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static cgtools.Random.random;
import static cgtools.Util.round;
import static cgtools.Vector.*;

public class TransparentMaterial implements Material {

    public final Color emission = new Color(0, 0, 0);
    public final Sampler albedo;
    public final double index1;
    public final double index2;

    private double EPSILON = 0.0001;

    public TransparentMaterial(Color color, double index1, double index2) {
        this.albedo = new ConstantColor(color);
        this.index1 = index1;
        this.index2 = index2;
    }

    public TransparentMaterial(Sampler texture, double index1, double index2) {
        this.albedo = texture;
        this.index1 = index1;
        this.index2 = index2;
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
        Direction d = incomingRay.direction;
        Direction n = hit.normal();
        double n1 = index1;
        double n2 = index2;
        // Swap n1 and n2 if necessary
        if (dotProduct(n, d) > 0) {
            n = negate(n);
            n1 = index2;
            n2 = index1;
        }
        Direction refract = refract(n, d, n1, n2);
        Direction scattered;
        if (refract == null) {
            scattered = reflect(n, d);
        } else if (random() > schlick(n, d, n1, n2)) {
            scattered = refract;
        } else scattered = reflect(n, d);
        Ray ray = new Ray(hit.position(), scattered, EPSILON, Double.POSITIVE_INFINITY);
        return new Properties(ray, emission, albedo.getColor(hit.u(), hit.v()));
    }

    public static Direction refract(Direction n, Direction d, double n1, double n2) {
        double r = n1 / n2;
        double c = dotProduct(negate(n), d);
        double discriminant = 1 - r * r * (1 - c * c);

        if (discriminant < 0) {
            return null;
        }
        return add(multiply(r, d), multiply(n, r * c - Math.sqrt(discriminant)));
    }

    public static Direction reflect(Direction n, Direction d) {
        // Return direction of reflected ray.
        Direction b = multiply(n, dotProduct(negate(d), n));
        return add(d, multiply(2, b));
    }

    public static double schlick(Direction n, Direction d, double n1, double n2) {
        double r0 = round(Math.pow((n1 - n2) / (n1 + n2), 2), 2);
        double product = dotProduct(n, d);
        double schlick = r0 + (1 - r0) * Math.pow(1 + product, 5);
        return schlick;
    }
}
