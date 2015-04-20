package infdta01.pkg1;

import java.io.FileNotFoundException;
import java.util.Map;

public class Testing {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String args[]) throws FileNotFoundException {
//        UserPreference calc = new UserPreference("UserItem.txt");
        UserPreference calc = new UserPreference("UserItem.txt");
//        UserPreference calc = new UserPreference("u.data");

        int userChoiceA = 1;
        int userChoiceB = 2;
        int itemChoiceA = 101;
        int itemChoiceB = 106;
        int totalClusters = 2;

        Map<Integer, Double> userA = calc.getUser(userChoiceA).getRatedItems();

        System.out.println("List for user: " + userChoiceA);
        for (Map.Entry<Integer, Double> item : userA.entrySet()) {
            System.out.println("item: " + item.getKey() + " value: " + item.getValue());
        }

        System.out.println("\n");

        Map<Integer, Double> userB = calc.getUser(userChoiceB).getRatedItems();

        System.out.println("List for user: " + userChoiceB);
        for (Map.Entry<Integer, Double> item : userB.entrySet()) {
            System.out.println("item: " + item.getKey() + " value: " + item.getValue());
        }

        System.out.println("\n");

        Pearson pearsonCalculation = new Pearson();
        pearsonCalculation.calcPearson(calc.getUser(userChoiceA).getRatedItems(), calc.getUser(userChoiceB).getRatedItems());
        pearsonCalculation.printPearson();

        Euclidean euclideanCalculation = new Euclidean();
        euclideanCalculation.calcEuclidean(calc.getUser(userChoiceA).getRatedItems(), calc.getUser(userChoiceB).getRatedItems());
        System.out.println("Euclidean: " + euclideanCalculation.getEuclidean());

        Cosine cosine = new Cosine();
        cosine.calcCosine(calc.getUser(userChoiceA).getRatedItems(), calc.getUser(userChoiceB).getRatedItems());
        System.out.println("Cosine: " + cosine.getCosine());

        System.out.println("-------------- " + itemChoiceA + " --------------");
        for (Map.Entry<Integer, User> entry : calc.mUserPreference.entrySet()) {
            if (entry.getValue().isRated(itemChoiceA)) {
                System.out.println("List for item: " + itemChoiceA + " >> " + entry.getValue().getRating(itemChoiceA));
            }
        }
        System.out.println("-------------- " + itemChoiceB + " --------------");
        for (Map.Entry<Integer, User> entry : calc.mUserPreference.entrySet()) {
            if (entry.getValue().isRated(itemChoiceB)) {
                System.out.println("List for item: " + itemChoiceB + " >> " + entry.getValue().getRating(itemChoiceB));
            }
        }

        int total = 0;
        System.out.println("-------------- both --------------");
        for (Map.Entry<Integer, User> entry : calc.mUserPreference.entrySet()) {
            if (entry.getValue().bothRated(itemChoiceA,itemChoiceB)) {
                System.out.println("User: " + entry.getKey());
                System.out.println("List for item: " + itemChoiceA + " >> " + entry.getValue().getRating(itemChoiceA));
                System.out.println("List for item: " + itemChoiceB + " >> " + entry.getValue().getRating(itemChoiceB));
                total++;
            }
        }

        // 2 total cluster
        int sampleNumber = 0;
        Kmeans kmeans = new Kmeans(totalClusters);
        kmeans.setTotalData(total);
        kmeans.sample = new double[total][2];

        for (Map.Entry<Integer, User> entry : calc.mUserPreference.entrySet()) {
            if (entry.getValue().bothRated(itemChoiceA,itemChoiceB)) {
                kmeans.sample[sampleNumber][0] = entry.getValue().getRating(itemChoiceA);
                kmeans.sample[sampleNumber][1] = entry.getValue().getRating(itemChoiceB);
                sampleNumber++;
            }
        }

        Kmeans.initialize();
        kmeans.kMeanCluster();
        kmeans.printDetails();

//        UserPreference calc2 = new UserPreference("/home/isaac/IdeaProjects/DataScience/src/infdta01/pkg1/ItemItem.txt");
//        SlopeOne slopeOne = new SlopeOne(calc2);
//        System.out.println("Dev: " + slopeOne.calculatePrediction(2, 103));

        SlopeOne slopeOne = new SlopeOne(calc);
        System.out.println("Dev: " + slopeOne.calculatePrediction(2, 103));
    }
}
