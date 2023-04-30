package cgg.a02;

import cgg.Image;
import cgtools.Sampler;

import static cgtools.Vector.*;

public class Main {

  /**
   * @param args
   */
  public static void main(String[] args) {
    final int width = 480;
    final int height = 270;

    // This class instance defines the contents of the image.
    // bei 0,0,0 sieht man nichts 

    // Creates an image and iterates over all pixel positions inside the image.
    Image image = new Image(width, height);
    Disc dsc=new Disc(width, height, 66);
    image.sample(dsc,100);
    // Write the image to disk.
    final String filename = "doc/a02-discs-supersamping.png";
    image.write(filename);
    System.out.println("Wrote image: " + filename);
    }
  }
