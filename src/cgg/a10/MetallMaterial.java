package cgg.a10;

import java.time.temporal.UnsupportedTemporalTypeException;

import cgg.a03.Hit;
import cgg.a03.Ray;
import cgg.a05.Material;
import cgg.a05.Properties;
import cgtools.*;

public class MetallMaterial implements Material {

    Color color;
    double scattering;

    public MetallMaterial(Color color, double scattering) {
        this.color = color;
        this.scattering = scattering;
    }

    public Ray scatteredRay(Ray ray, Hit hit) {
        Direction r = Vector.subtract(ray.getDirection(),
            Vector.multiply(2 * Vector.dotProduct(hit.getNormVec(), ray.getDirection()), hit.getNormVec()));
        Random rand = new Random();
        double epsilon = 0.00000001;
        
        while(true) {
            double x = (rand.nextDouble() * 2.0) - 1.0;
            double y = (rand.nextDouble() * 2.0) - 1.0;
            double z = (rand.nextDouble() * 2.0) - 1.0;
            
            if(scattering != 0) {
                Direction randomDir = new Direction(x, y, z);
                r = Vector.add(r, Vector.multiply(scattering, randomDir));
            }
            return new Ray(hit.getHitPoint(), r, epsilon, Double.POSITIVE_INFINITY);
        }
    }

    public Color albedo() {
        return color;
    }

    public Color emission() {
        return Vector.black;
    }

    @Override
    public int width() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'width'");
    }

    @Override
    public int height() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'height'");
    }

    @Override
    public Properties properties(Ray incomingRay, Hit hit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'properties'");
    }

    
}
