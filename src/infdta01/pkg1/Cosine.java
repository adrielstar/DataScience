package infdta01.pkg1;

import java.util.Map;

/**
 * @author isaacdecuba
 */
public class Cosine {

    private double cosine;

    // persons to compare
    public double[] personA;
    public double[] personB;

    /**
     * Calculates the distance between 2 persons using cosine
     *
     * @param personARatedItems
     * @param personBRatedItems
     * @return cosine
     */
    public double calcCosine(Map<Integer, Double> personARatedItems, Map<Integer, Double> personBRatedItems) {

        if (personARatedItems.size() > personBRatedItems.size()) {
            personA = new double[personARatedItems.size() + 1];
            personB = new double[personARatedItems.size() + 1];
        } else {
            personA = new double[personBRatedItems.size() + 1];
            personB = new double[personBRatedItems.size() + 1];
        }

        int pos = 1;
        double resultA = 0.0;
        double resultB = 0.0;
        double resultM = 0.0;

        for (Map.Entry<Integer, Double> entry : personARatedItems.entrySet()) {
            resultA += personA[pos] = Math.pow(entry.getValue(), 2);
            pos++;
        }

        pos = 1;
        for (Map.Entry<Integer, Double> entry : personBRatedItems.entrySet()) {
            resultB += personB[pos] = Math.pow(entry.getValue(), 2);
            pos++;
        }

        resultA = Math.abs(Math.sqrt(resultA));
        resultB = Math.abs(Math.sqrt(resultB));

        Filter filter = new Filter(personARatedItems, personBRatedItems);
        personA = filter.getPersonA();
        personB = filter.getPersonB();

        for (int i = 0; i < personA.length; i++) {
            resultM += personA[i] * personB[i];
        }

        return cosine = resultM / (resultA * resultB);
    }

    public double getCosine() {
        return this.cosine;
    }
}
