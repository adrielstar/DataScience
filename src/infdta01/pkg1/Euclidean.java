package infdta01.pkg1;

import java.util.Map;

public class Euclidean implements Similarity {

    private double mEuclidean;

    /**
     * This method is used to calculate the Euclidean in K-means
     * @param d
     * @param c
     * @return double distance
     */
    public double calculate(Data d, Centroid c) {
        return mEuclidean = Math.sqrt(Math.pow((c.getY() - d.getY()), 2) + Math.pow((c.getX() - d.getX()), 2));
    }

    @Override
    public double calculate(Map<Integer, Double> personARatedItems, Map<Integer, Double> personXRatedItems){
        Filter filter = new Filter(personARatedItems, personXRatedItems);
        double[] ratingPersonA = filter.getPersonA();
        double[] ratingPersonX = filter.getPersonB();

        // length ratingP A can differ from ratingX
        double result = 0.0;

        for (int i = 0; i < ratingPersonA.length; i++) {
            result += Math.pow(Math.abs(ratingPersonA[i] - ratingPersonX[i]), 2);
        }
        return Math.sqrt(result);
    }

    public double getEuclidean() {
        return this.mEuclidean;
    }
}
