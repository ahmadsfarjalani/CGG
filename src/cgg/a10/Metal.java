package cgg.a10;

import cgtools.*;

import static cgtools.Vector.*;

import javax.swing.plaf.ColorUIResource;

import cgg.a03.Hit;
import cgg.a03.Ray;
import cgg.a05.Material;
import cgg.a05.Properties;

public record Metal(Color albedo) implements Material {

    public Color albedo (Ray r , Hit h) {
        return albedo;
    }
    public Color emission(Ray r , Hit h) {
        return black;
    }
    public Ray scatteredRay(Ray r , Hit h) {
        return new Ray (
            h.x(),
            subtract(
                r.d(),
                multiply(
                    2*dotProduct(r.d(), h.n()),
                    h.n())),
                    Util.EPSILON, Double.POSITIVE_INFINITY);

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
    

