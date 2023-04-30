package cgg.a02;

import cgtools.Color;

public class Schachbrett {

    private double groesse;
    private Color color1;
    private Color color2;

    public Schachbrett(double groesse, Color color1, Color color2) {
        this.groesse = groesse;
        this.color1 = color1;
        this.color2 = color2;
    }

    public Color getColor(double x, double y) {
        int cellX = (int) Math.floor(x / groesse);
        int cellY = (int) Math.floor(y / groesse);
        if ((cellX + cellY) % 2 == 0) {
            return color1;
        } else {
            return color2;
        }
    }
}
