/**
 * @author henrik.tramberend@beuth-hochschule.de
 */
package cgg;

import cgg.a07.OnePixel;
import cgg.a07.Pixel;
import cgtools.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Image {

    public static final int samplePoints = 300;
    public static final int width = 1280;
    public static final int height = 720;

    private final int components = 3;
    private double[] data;

    public Image() {
        this.data = new double[width * height * components];
    }

    public void setPixel(int x, int y, Color color) {
        int indexRed = components * (y * width + x);
        int indexGreen = indexRed + 1;
        int indexBlue = indexRed + 2;

        color = correctGamma(color);

        data[indexRed] = color.r();
        data[indexGreen] = color.g();
        data[indexBlue] = color.b();
    }

    private Color correctGamma(Color color) {
        final double gamma = 2.2;
        double r = Math.pow(color.r(), 1 / gamma);
        double g = Math.pow(color.g(), 1 / gamma);
        double b = Math.pow(color.b(), 1 / gamma);
        return new Color(r, g, b);
    }

    public void write(String filename) {
        ImageWriter.write(filename, data, width, height);
    }

    public void sample(Sampler s) {
        int threads = Runtime.getRuntime().availableProcessors();
        ExecutorService pool = Executors.newFixedThreadPool(threads);
        List<Future<Pixel>> pixels = new ArrayList<>();
        // Iterates over all pixel positions inside this image.
        for (int x = 0; x != width; x++) {
            for (int y = 0; y != height; y++) {
                pixels.add(pool.submit(new OnePixel(s, x, y)));
            }
        }
        int i = 0;
        for (Future<Pixel> future : pixels) {
            try {
                Pixel pixel = future.get();
                // Sets the color for one particular pixel.
                this.setPixel(pixel.x(), pixel.y(), pixel.color());
                if (pixel.x() % (width / 20) == 0 && pixel.y() == 0) {
                    System.out.print(i + "%... ");
                    i += 5;
                }
            } catch (InterruptedException | ExecutionException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        pool.shutdown();
    }

    private void notYetImplemented() {
        System.err.println("Please complete the implementation of class cgg.Image as part of assignment 1.");
        System.exit(1);
    }

    public static String getFilepath(String filename) {
        String projectDir = System.getProperty("user.dir");
        return projectDir + "/doc/" + filename;
    }
}
