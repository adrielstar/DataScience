package infdta01.pkg1;

/**
 *
 * @author isaacdecuba
 */
public final class Data {

    private double mX = 0;
    private double mY = 0;
    private int mCluster = 0;

    public Data() {
    }

    public Data(double x, double y) {
        setX(x);
        setY(y);
    }

    public void setX(double x) {
        mX = x;
    }

    public double getX() {
        return mX;
    }

    public void setY(double y) {
        this.mY = y;
    }

    public double getY() {
        return mY;
    }

    public void setCluster(int clusterNumber) {
        mCluster = clusterNumber;
    }

    public int getCluster() {
        return mCluster;
    }
}
