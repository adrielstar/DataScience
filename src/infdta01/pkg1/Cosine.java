/**
 * Cosine class containing similarity calculation between two users
 *
 * @author Isaac de Cuba (isaacjdecuba@gmail.com)
 * @studentnr 0847325
 * @since 05-06-2015
 */
package infdta01.pkg1;

import java.util.Map;

public class Cosine implements Similarity {

    // persons to compare
    public double[] personA;
    public double[] personB;

    @Override
    public double calculate(Map<Integer, Double> personARatedItems, Map<Integer, Double> personBRatedItems) {

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

        return resultM / (resultA * resultB);
    }
}
