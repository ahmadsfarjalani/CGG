package cgg.a03;

import cgtools.Direction;
import cgtools.Matrix;
import cgtools.Point;

import static cgtools.Vector.*;

public class Ray {

    public final Point source;
    public final Direction direction;
    public final double tmin;
    public final double tmax;

    public Ray(Point source, Direction direction, double tmin, double tmax) {
        this.source = source;
        this.direction = direction;
        this.tmin = tmin;
        this.tmax = tmax;
    }

    public Ray transform(Matrix m) {
        Point source = Matrix.multiply(m, this.source);
        Direction direction = Matrix.multiply(m, this.direction);
        return new Ray(source, direction, this.tmin, this.tmax);
    }

    public Ray transform(Matrix sourceMatrix, Matrix directionMatrix) {
        Point source = Matrix.multiply(sourceMatrix, this.source);
        Direction direction = Matrix.multiply(directionMatrix, this.direction);
        return new Ray(source, direction, this.tmin, this.tmax);
    }

    public Point pointAt(double t) {
        return add(source, multiply(direction, t));
    }

    public boolean isValid(double t) {
        return t >= tmin && t <= tmax;
    }
}
