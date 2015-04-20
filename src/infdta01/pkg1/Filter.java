package infdta01.pkg1;

import java.util.Map;

public class Filter {

    private final double[] ratingPersonA;
    private final double[] ratingPersonB;

    /**
     * Filters the two Maps and put the same rated items into array
     *
     * @param personARatedItems
     * @param personBRatedItems
     */
    public Filter(Map<Integer, Double> personARatedItems, Map<Integer, Double> personBRatedItems) {

        int count = 0;
        int pos = 0;
        if (personARatedItems.size() < personBRatedItems.size()) {
            for (Map.Entry<Integer, Double> entry : personARatedItems.entrySet()) {
                if (personBRatedItems.containsKey(entry.getKey())) {
                    count++;
                }
            }
            ratingPersonA = new double[count];
            ratingPersonB = new double[count];

            for (Map.Entry<Integer, Double> entry : personARatedItems.entrySet()) {
                if (personBRatedItems.containsKey(entry.getKey())) {
                    ratingPersonA[pos] = (double) personARatedItems.get(entry.getKey());
                    ratingPersonB[pos] = (double) personBRatedItems.get(entry.getKey());
                    pos++;
                }
            }
        } else {
            for (Map.Entry<Integer, Double> entry : personARatedItems.entrySet()) {
                if (personBRatedItems.containsKey(entry.getKey())) {
                    count++;
                }
            }
            ratingPersonA = new double[count];
            ratingPersonB = new double[count];

            for (Map.Entry<Integer, Double> entry : personBRatedItems.entrySet()) {
                if (personARatedItems.containsKey(entry.getKey())) {
                    ratingPersonA[pos] = (double) personBRatedItems.get(entry.getKey());
                    ratingPersonB[pos] = (double) personARatedItems.get(entry.getKey());
                    pos++;
                }
            }
        }
    }

    public double[] getPersonA() {
        return this.ratingPersonA;
    }

    public double[] getPersonB() {
        return this.ratingPersonB;
    }
}
