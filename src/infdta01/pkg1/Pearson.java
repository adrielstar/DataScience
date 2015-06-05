/**
 * Pearson class containing similarity calculation between two users
 *
 * @author Isaac de Cuba (isaacjdecuba@gmail.com)
 * @studentnr 0847325
 * @since 05-06-2015
 */
package infdta01.pkg1;

import java.util.Map;

public class Pearson implements Similarity {

    public double mPearson;

    /**
     * Calculates the first part of the numeration
     *
     * @param ratingPersonA
     * @param ratingPersonB
     * @return double result
     */
    private double calcNumeratorOne(double[] ratingPersonA, double[] ratingPersonB) {
        int n = ratingPersonA.length;
        double result = 0.0;

        for (int i = 0; i < n; i++) {
            result += ratingPersonA[i] * ratingPersonB[i];
        }

        return result;
    }

    /**
     * Calculates the second part of the numeration
     *
     * @param ratingPersonA
     * @param ratingPersonB
     * @return double result
     */
    private double calcNumeratorTwo(double[] ratingPersonA, double[] ratingPersonB) {
        int n = ratingPersonA.length;
        double sumA = this.calcSumArray(ratingPersonA);
        double sumB = this.calcSumArray(ratingPersonB);

        return (sumA * sumB) / n;
    }

    /**
     * Calculates numeration
     *
     * @param ratingPersonA
     * @param ratingPersonB
     * @return double result
     */
    private double calcNumeration(double[] ratingPersonA, double[] ratingPersonB) {
        double numeratorOne = this.calcNumeratorOne(ratingPersonA, ratingPersonB);
        double numeratorTwo = this.calcNumeratorTwo(ratingPersonA, ratingPersonB);

        return numeratorOne - numeratorTwo;
    }

    /**
     * Calculates the denominator of a specific array
     *
     * @param ratingPerson
     * @return double result
     */
    private double calcDenominator(double[] ratingPerson) {
        int n = ratingPerson.length;
        double sumA = 0.0;

        for (int i = 0; i < n; i++) {
            sumA += Math.pow(ratingPerson[i], 2);
        }

        return Math.sqrt(sumA - (Math.pow(this.calcSumArray(ratingPerson), 2) / n));
    }

    /**
     * Calculates the sum of a specific array
     *
     * @param ratingPerson
     * @return double sum
     */
    private double calcSumArray(double[] ratingPerson) {
        double sum = 0.0;

        for (int i = 0; i < ratingPerson.length; i++) {
            sum += ratingPerson[i];
        }

        return sum;
    }

    @Override
    public double calculate(Map<Integer, Double> personARatedItems, Map<Integer, Double> personBRatedItems) {
        Filter filter = new Filter(personARatedItems, personBRatedItems);
        double[] ratingPersonA = filter.getPersonA();
        double[] ratingPersonB = filter.getPersonB();

        double numeration = this.calcNumeration(ratingPersonA, ratingPersonB);
        double denominator = this.calcDenominator(ratingPersonA) * this.calcDenominator(ratingPersonB);

        return mPearson = numeration / denominator;
    }
}
