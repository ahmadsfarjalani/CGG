package cgg.a02;

import cgtools.Color;
import cgtools.Random;
import cgtools.Sampler;

import java.util.ArrayList;
import java.util.List;

public class ColoredDiscs implements Sampler {

    private List<Disc> discs;

    public ColoredDiscs(int amount, int maxX, int maxY) {
        discs = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < amount; i++) {
            // Create random properties for the disc.
            int x = random.nextInt(maxX);
            int y = random.nextInt(maxY);
            int radius = random.nextInt(maxY / 2);
            double r = random.nextDouble();
            double g = random.nextDouble();
            double b = random.nextDouble();
            Color color = new Color(r, g, b);
            // Create a disc object and add it to the list.
            Disc disc = new Disc(x, y, radius, color);
            discs.add(disc);
            // Sort list by radius.
            discs.sort(new DiscComparator());
        }
    }

    public int width() {
        return 0;
    }

    public int height() {
        return 0;
    }

    public Color getColor(double x, double y) {
        Color backgroundColor = new Color(0 , 0, 0);
        for (Disc disc: discs) {
            if (disc.isPointInDisc(x, y)) {
                return disc.getColor();
            }
        }
        return backgroundColor;
    }

    public static void main(String[] args) {
        ColoredDiscs d = new ColoredDiscs(10, 480, 270);
        System.out.println(d.discs);
    }
}
