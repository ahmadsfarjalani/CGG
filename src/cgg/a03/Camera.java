package cgg.a03;

import cgtools.Direction;
import cgtools.Matrix;

import static cgtools.Vector.*;

public class Camera {

    private double angle;
    private double width;
    private double height;
    private Matrix matrix;

    public Camera(double angle, double width, double height) {
        this.angle = angle;
        this.width = width;
        this.height = height;
        this.matrix = Matrix.identity();
    }

    public Camera(double angle, double width, double height, Matrix matrix) {
        this.angle = angle;
        this.width = width;
        this.height = height;
        this.matrix = matrix;
    }

    /**
     * Generates a ray which starts at the camera location, usually (0, 0, 0), and points to the specified pixel.
     * @param x the x coordinate of the pixel
     * @param y the y coordinate of the pixel
     * @return a Ray object.
     */
    public Ray generateRay(double x, double y) {
        double halfWidth = width / 2;
        double halfHeight = height / 2;

        // Calculate x, y, and z coordinates of the direction vector.
        double xdir = x - halfWidth;
        double ydir = halfHeight - y;
        double zdir = -(halfWidth / Math.tan(angle / 2));
        Direction normal = normalize(direction(xdir, ydir, zdir));

        Ray ray = new Ray(point(0, 0, 0), normal, 0, Double.POSITIVE_INFINITY);
        return ray.transform(matrix);
    }
}
