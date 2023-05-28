package cgg.a05;

import cgg.a01.ConstantColor;
import cgg.a03.Hit;
import cgg.a03.Ray;
import cgtools.Color;
import cgtools.Sampler;

public class BackgroundMaterial implements Material {

    public final Sampler albedo = new ConstantColor(new Color(0, 0, 0));
    public final Sampler emission;
    private final Ray scatteredRay = null;

    public BackgroundMaterial(Color color) {
        this.emission = new ConstantColor(color);
    }

    public BackgroundMaterial(Sampler texture) {
        this.emission = texture;
    }

    public int width() {
        return emission.width();
    }

    public int height() {
        return emission.height();
    }

    @Override
    public Properties properties(Ray incomingRay, Hit hit) {
        return new Properties(scatteredRay, emission.getColor(hit.u(), hit.v()), albedo.getColor(hit.u(), hit.v()));
    }
}
