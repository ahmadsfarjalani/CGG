package cgg.a05;

import cgtools.*;

public interface Material {
    public Ray scatteredRay(Ray ray, Hit hit);

    public Color albedo();

    public Color emission();
}
