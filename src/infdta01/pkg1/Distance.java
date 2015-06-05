package infdta01.pkg1;

public class Distance {

    /**
     * Calculate similarity/ distance
     *
     * @param up
     * @param userIdA
     * @param userIdB
     * @param calcDistanceMethod
     * @return similarity | null
     */
    public Double getDistance(UserPreference up, int userIdA, int userIdB, String calcDistanceMethod) {
        if (up == null) {
            return null;
        }

        if (calcDistanceMethod.equalsIgnoreCase("EUCLIDEAN")) {
            Euclidean method = new Euclidean();
            return method.calculate(up.getUser(userIdA).getRatedItems(), up.getUser(userIdB).getRatedItems());
        } else if (calcDistanceMethod.equalsIgnoreCase("COSINE")) {
            Cosine method = new Cosine();
            return method.calculate(up.getUser(userIdA).getRatedItems(), up.getUser(userIdB).getRatedItems());
        } else if (calcDistanceMethod.equalsIgnoreCase("PEARSON")) {
            Pearson method = new Pearson();
            return method.calculate(up.getUser(userIdA).getRatedItems(), up.getUser(userIdB).getRatedItems());
        }
        return null;
    }

    /**
     * Prints the d
     * @param up
     * @param userIdA
     * @param userIdB
     * @param calcDistanceMethod
     */
    public void printDistance(UserPreference up, int userIdA, int userIdB, String calcDistanceMethod) {
        if (up == null) {
            return;
        }

        if (calcDistanceMethod.equalsIgnoreCase("EUCLIDEAN")) {
            System.out.println("Euclidean distance user id: " + userIdA + " and user id: " + userIdB + " is " + getDistance(up, userIdA, userIdB, calcDistanceMethod));
        } else if (calcDistanceMethod.equalsIgnoreCase("COSINE")) {
            System.out.println("Cosine similarity between user id: " + userIdA + " and user id: " + userIdB + " is " + getDistance(up, userIdA, userIdB, calcDistanceMethod));
        } else if (calcDistanceMethod.equalsIgnoreCase("PEARSON")) {
            System.out.println("Pearson correlation between user id: " + userIdA + " and user id: " + userIdB + " is " + getDistance(up, userIdA, userIdB, calcDistanceMethod));
        } else {
            System.out.println("Distance method is not correct");
        }
    }
}
