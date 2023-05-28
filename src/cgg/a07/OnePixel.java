package cgg.a07;

import cgg.Image;
import cgtools.Color;
import cgtools.Random;
import cgtools.Sampler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public record OnePixel(Sampler s, int x, int y) implements Callable<Pixel> {

    public Pixel call() {
        int samplePoints = Image.samplePoints;
        List<Color> pixelColors = new ArrayList<>();
        // Get the color from a random point in the pixel 100 times and add it to a list.
        for (int i = 0; i < samplePoints; i++) {
            double scanX = this.x + Random.random();
            double scanY = this.y + Random.random();
            pixelColors.add(this.s.getColor(scanX, scanY));
        }
        // Calculate the average from all colors in the list.
        Color color = pixelColors.remove(0);
        for (Color c : pixelColors) {
            double r = color.r() + c.r();
            double g = color.g() + c.g();
            double b = color.b() + c.b();
            color = new Color(r, g, b);
        }
        double r = color.r() / samplePoints;
        double g = color.g() / samplePoints;
        double b = color.b() / samplePoints;
        return new Pixel(this.x, this.y, new Color(r, g, b));
    }
}
