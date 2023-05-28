package cgg.a02;

import cgg.Image;
import cgtools.Sampler;

public class Main {

    public static void main(String[] args) {
        final int width = 480;
        final int height = 270;

        Image discs = new Image();
        discs.sample(new ColoredDiscs(50, width, height));
        discs.write(getFilepath("a02-discs.png"));

        Image discsSupersampling = new Image();
        discsSupersampling.sample(new ColoredDiscs(50, width, height));
        discsSupersampling.write(getFilepath("a02-discs-supersampling.png"));
    }

    private static String getFilepath(String filename) {
        String projectDir = System.getProperty("user.dir");
        return projectDir + "/doc/" + filename;
    }
}
