package infdta01.pkg1;

/**
 *
 * @author isaacdecuba
 */
public class Centroid {

    private double mX = 0.0;
    private double mY = 0.0;

    public Centroid() {
    }

    public Centroid(double newX, double newY) {
        mX = newX;
        mY = newY;
    }

    public void setX(double newX) {
        mX = newX;
    }

    public double getX() {
        return mX;
    }

    public void setY(double newY) {
        mY = newY;
    }

    public double getY() {
        return mY;
    }
}
