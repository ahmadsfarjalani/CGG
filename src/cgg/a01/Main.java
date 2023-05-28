package cgg.a01;

import cgg.*;
import cgtools.Color;
import cgtools.Sampler;

public class Main {

  public static void main(String[] args) {
    final int width = 480;
    final int height = 270;

    createConstantColor(width, height, "/doc/testimage.png");
    createDisc(width, height, "/doc/a01-disc.png");
    createPolkaDots(width, height, "/doc/a01-polka-dots.png");
  }

  private static void createConstantColor(int width, int height, String path) {
    // This class instance defines the contents of the image.
    Sampler content = new ConstantColor(new Color(90, 90, 90));
    // Create an image using the content object.
    Image image = createImage(width, height, content);
    // Write the image to disk.
    writeImage(image, path);
  }

  private static void createDisc(int width, int height, String path) {
    Color circleColor = new Color(0.506, 0.855, 0.988);
    Color backgroundColor = new Color(0, 0, 0);
    // This class instance defines the contents of the image.
    Sampler content = new Disc(circleColor, backgroundColor, 100, width / 2, height / 2);
    // Create an image using the content object.
    Image image = createImage(width, height, content);
    // Write the image to disk.
    writeImage(image, path);
  }

  private static void createPolkaDots(int width, int height, String path) {
    Color circleColor = new Color(0.5, 1.0, 0.5);
    Color backgroundColor = new Color(0, 0, 0);
    // This class instance defines the contents of the image.
    Sampler content = new PolkaDots(circleColor, backgroundColor, 23, 5, 9, width, height);
    // Create an image using the content object.
    Image image = createImage(width, height, content);
    // Write the image to disk.
    writeImage(image, path);
  }

  private static Image createImage(int width, int height, Sampler content) {
    // Creates an image and iterates over all pixel positions inside the image.
    Image image = new Image();
    for (int x = 0; x != width; x++) {
      for (int y = 0; y != height; y++) {
        // Sets the color for one particular pixel.
        image.setPixel(x, y, content.getColor(x, y));
      }
    }
    return image;
  }

  private static void writeImage(Image image, String path) {
    String projectDir = System.getProperty("user.dir");
    String filename = projectDir + path;
    image.write(filename);
    System.out.println("Wrote image: " + filename);
  }
}