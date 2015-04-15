package infdta01.pkg1;

import java.util.Map;

/**
 *
 * @author isaacdecuba
 */
public class Euclidean {

    private double euclidean;

    /**
     * Calculates the Euclidean between the 2 persons in the parameters
     *
     * @param personARatedItems
     * @param personXRatedItems
     * @return
     */
    public double calcEuclidean(Map<Integer, Double> personARatedItems, Map<Integer, Double> personXRatedItems) {
        Filter filter = new Filter(personARatedItems, personXRatedItems);
        double[] ratingPersonA = filter.getPersonA();
        double[] ratingPersonX = filter.getPersonB();

        // length ratingP A can differ from ratingX
        double result = 0.0;

        for (int i = 0; i < ratingPersonA.length; i++) {
            result += Math.pow(Math.abs(ratingPersonA[i] - ratingPersonX[i]), 2);
        }
        result = euclidean = Math.sqrt(result);
        return result;
    }
    
    /**
     * This method is used to calculate the Euclidean in K-means
     * @param d
     * @param c
     * @return double distance
     */
    public double calcEuclidean(Data d, Centroid c) {
        return euclidean = Math.sqrt(Math.pow((c.getY() - d.getY()), 2) + Math.pow((c.getX() - d.getX()), 2));
    }

    public double getEuclidean() {
        return this.euclidean;
    }
}
