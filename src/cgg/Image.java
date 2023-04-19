/** @author henrik.tramberend@beuth-hochschule.de */
package cgg;

import cgtools.*;

public class Image {
  double[] data;
  int width;
  int height;

  /**
   * @param width
   * @param height
   */
  public Image(int width, int height) {
    this.width = width;
    this.height = height;
    data = new double[width * height* 3];
  }

  /**
   * @param x
   * @param y
   * @param color
   */
  public void setPixel(int x, int y, Color color) {
    final int imageIndex = (y * width + x) * 3;
    data [imageIndex + 0] = color.r();
    data [imageIndex + 1] = color.g();
    data [imageIndex + 2] = color.b();
  }

  /**
   * @param filename
   */
  public void write(String filename) {
    ImageWriter.write(filename  , data, width , height ) ;
    System.out.println("Wrote image to  " + filename);
  }

  //private void notYetImplemented() {
    //System.err.println("Please complete the implementation of class cgg.Image as part of assignment 1.");
    //System.exit(1);
  //}

}
