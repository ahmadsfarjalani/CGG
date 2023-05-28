package cgg.a05;

import cgg.a03.Hit;
import cgg.a03.Ray;

public interface Material {

    int width();

    int height();

    Properties properties(Ray incomingRay, Hit hit);
}
