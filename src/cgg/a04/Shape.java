package cgg.a04;

import cgg.a03.Hit;
import cgg.a03.Ray;

public interface Shape {

    Hit intersect(Ray ray);

}
