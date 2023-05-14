/** @author henrik.tramberend@beuth-hochschule.de */
package cgtools;

// See
// https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Record.html
// for details on the record data type.
public record Color(double r, double g, double b) {

  public static Color white;

@Override
  public String toString() {
    return String.format("(Col: %.2f %.2f %.2f)", r, g, b);
  }

public double getBlue() {
    return 0;
}

public double getRed() {
    return 0;
}

public double getGreen() {
    return 0;
}

public int getRGB() {
    return 0;
}

public static Color color(double d, int i, int j) {
  return null;
}

public Color add(Color currentPixelColor) {
    return null;
}

public Color divide(Object sampleRate) {
    return null;
}
}
