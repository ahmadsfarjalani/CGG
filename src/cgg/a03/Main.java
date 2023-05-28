package cgg.a03;

import cgg.Image;

public class Main {

    public static void main(String[] args) {
        final int width = 1280;
        final int height = 720;

        Image image = new Image();
        image.sample(new ThreeSpheres(0.2));
        image.write(getFilepath("a03-three-spheres.png"));
    }

    private static String getFilepath(String filename) {
        String projectDir = System.getProperty("user.dir");
        return projectDir + "/doc/" + filename;
    }
}