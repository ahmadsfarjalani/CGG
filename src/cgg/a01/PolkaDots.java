package cgg.a01;

import cgtools.Color;
import cgtools.Sampler;

import java.util.ArrayList;
import java.util.List;

public class PolkaDots implements Sampler {

    private Color circleColor;
    private Color backgroundColor;
    private int radius;
    private int rows;
    private int columns;
    private int width;
    private int height;
    private int xgap;
    private int ygap;

    public PolkaDots(Color circleColor, Color backgroundColor, int radius, int rows, int columns, int width, int height) {
        this.circleColor = circleColor;
        this.backgroundColor = backgroundColor;
        this.radius = radius;
        this.rows = rows;
        this.columns = columns;
        this.width = width;
        this.height = height;
        this.xgap = calculateGapSizeX();
        this.ygap = calculateGapSizeY();
    }

    public int width() {
        return 0;
    }
 
    public int height() {
        return 0;
    }

    public Color getColor(double x, double y) {
        // Create a list with the coordinates of each circles center pixel.
        List<int[]> centerPixels = getCenterPixels();
        // Iterate through each center pixel.
        for (int[] pixel : centerPixels) {
            // create a disc object at the coordinates and check if the pixel at (x,y) is inside the disc.
            Disc d = new Disc(circleColor, backgroundColor, radius, pixel[0], pixel[1]);
            if (d.getColor(x, y).equals(circleColor)) {
                return circleColor;
            }
        }
        return backgroundColor;
    }

    private List<int[]> getCenterPixels() {
        // Create a list with the coordinates of each circles center pixel.
        List<int[]> centerPixels = new ArrayList<>();
        int xpos = xgap + radius;
        int ypos = ygap + radius;
        // Iterate through every row and column and add the center pixel of each circle to the list.
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                centerPixels.add(new int[]{xpos, ypos});
                // Calculate the x coordinate of the next circle.
                xpos += xgap + radius * 2;
            }
            // Calculate the y coordinate of the next circle.
            ypos += ygap + radius * 2;
            // Reset x coordinate after each iteration.
            xpos = xgap + radius;
        }
        return centerPixels;
    }

    private int calculateGapSizeX() {
        // Return the size of the gap between circles on the x axis.
        int coloredPixels = radius * 2 * columns;
        int freePixels = width - coloredPixels;
        int gaps = columns + 1;
        int gapSize = freePixels / gaps;
        return gapSize;
    }

    private int calculateGapSizeY() {
        // Return the size of the gap between circles on the y axis.
        int coloredPixels = radius * 2 * rows;
        int freePixels = height - coloredPixels;
        int gaps = rows + 1;
        int gapSize = freePixels / gaps;
        return gapSize;
    }

}
