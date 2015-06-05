package infdta01.pkg1;

import java.util.Map;

public interface Similarity {

    /**
     * Calculates the distance between 2 persons
     *
     * @param personARatedItems
     * @param personBRatedItems
     * @return similarity
     */
    public double calculate(Map<Integer, Double> personARatedItems, Map<Integer, Double> personBRatedItems);
}
