/** @author henrik.tramberend@beuth-hochschule.de */
package cgg.a01;

import cgtools.*;

// Represents the contents of an image. Provides the same color for all pixels.
public record ConstantColor(Color color) implements Sampler {

  public int width() {
    return 1;
  }

  public int height() {
    return 1;
  }

  @Override
  public Color getColor(double u, double v) {
    return color;
  }
}
