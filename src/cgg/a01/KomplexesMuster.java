package cgg.a01;

import cgtools.Color;
public class KomplexesMuster {

    private double groesse;


    public KomplexesMuster(double groesse) {
        this.groesse = groesse;
    }

    public Color getColor(double x, double y,Color color1 ,Color color2) {
        int cellX = (int) Math.floor(x / groesse);      // Zeile
        int cellY = (int) Math.floor(y / groesse);      // Spalte
        if ((cellX * cellY) % 3 == 0 && (cellX + cellY) % 2 == 1 && cellX % 2 == 0) {
            return color1 ;
        }
        else {
            return color2 ;
        }
    }
}
