package cgg;

import java.util.ArrayList;

import cgg.a03.CameraObscura;
import cgg.a03.Hit;
import cgg.a03.Ray;
import cgg.a03.RayTracing;
import cgg.a03.Sphere;
import cgtools.Color;
import cgtools.ImageWriter;
import cgtools.Point;

public class Image {

  double [] array;
  int width;
  int height;

  public Image(int width, int height) {
    this.width=width;
    this.height=height;
    this.array= new double[width*height*3];
  }

  public void setPixel(int x, int y, Color color) {
    //zu weisung der pixel 
    int i = 3 * (y * width + x);
    double gamma = 0.5;
    array[i + 0] = Math.pow(color.r(), 1/gamma);
    array[i + 1] = Math.pow(color.g(), 1/gamma);
    array[i + 2] = Math.pow(color.b(), 1/gamma);
  }

  public void write(String filename) {
    ImageWriter.write(filename,array,width,height);
  }
  public void sample(int sampleRate, ArrayList<Sphere> spheres, CameraObscura camera) {
    Color bottomColor = new Color(0.2f, 0.3f, 0.4f); // Ändern Sie die Werte für die untere Farbe des Farbverlaufs
    Color topColor = new Color(0.4f, 0.3f, 0.2f); // Ändern Sie die Werte für die obere Farbe des Farbverlaufs

    for (int xPosition = 0; xPosition < width; xPosition++) {
        for (int yPosition = 0; yPosition < height; yPosition++) {
            Color accumulatedColor = new Color(0, 0, 0);
            for (int sampleIndex = 0; sampleIndex < sampleRate; sampleIndex++) {
                Ray ray = camera.generateRay(xPosition, yPosition);
                
                Hit nearestHit = null;
                for (Sphere sphere : spheres) {
                    Hit hit = sphere.intersect(ray);
                    if (hit != null) {
                        if ((nearestHit == null) || (hit.getRayParameterT() < nearestHit.getRayParameterT())) {
                            nearestHit = hit;
                        }
                    }
                }

                Color currentPixelColor;
                if (nearestHit != null) {
                    currentPixelColor = RayTracing.shade(nearestHit.getNormVec(), nearestHit.getHitPointColor());
                } else {
                    // Interpolate between the bottom and top colors based on the current y position
                    float t = (float) yPosition / (float) height;
                    currentPixelColor = new Color(
                        bottomColor.r() * (1 - t) + topColor.r() * t,
                        bottomColor.g() * (1 - t) + topColor.g() * t,
                        bottomColor.b() * (1 - t) + topColor.b() * t
                    );
                }
                accumulatedColor = new Color(accumulatedColor.r() + currentPixelColor.r(), accumulatedColor.g() + currentPixelColor.g(), accumulatedColor.b() + currentPixelColor.b());
            }
            Color finalColor = new Color(accumulatedColor.r() / sampleRate, accumulatedColor.g() / sampleRate, accumulatedColor.b() / sampleRate);
            setPixel(xPosition, yPosition, finalColor);
        }
    }
}

  public void addTriangle(Point point, Point point2, Point point3, Color triangleColor) {
  }
  
}