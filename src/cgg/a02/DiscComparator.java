package cgg.a02;

import java.util.Comparator;

public class DiscComparator implements Comparator<Disc> {

    @Override
    public int compare(Disc o1, Disc o2) {
        return Integer.compare(o1.getRadius(), o2.getRadius());
    }
}
