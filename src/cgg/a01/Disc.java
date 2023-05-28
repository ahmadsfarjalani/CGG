package cgg.a01;

import cgtools.Color;
import cgtools.Sampler;

public class Disc implements Sampler {

    Color discColor;
    Color backgroundColor;
    int radius;
    int xPos;
    int yPos;

    public Disc(Color discColor, Color backgroundColor, int radius, int xPos, int yPos) {
        this.discColor = discColor;
        this.backgroundColor = backgroundColor;
        this.radius = radius;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public int width() {
        return 0;
    }

    public int height() {
        return 0;
    }

    public Color getColor(double x, double y) {
        // offset x and y so that the center of the image has the coordinates (0, 0).
        x = x - xPos;
        y = y - yPos;
        // determine if the pixel is inside the circle.
        double result = x * x + y * y - Math.pow(radius, 2);
        if (result < 0) {
            return discColor;
        }
        return backgroundColor;
    }
}
