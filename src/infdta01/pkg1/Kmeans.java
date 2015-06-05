package infdta01.pkg1;

import java.util.ArrayList;

public class Kmeans {
    public double sample[][];
    public Euclidean euclidean;
    private int mNumberOfCluster;
    private int mTotalData;

    private static final ArrayList<Data> dataSet = new ArrayList<>();
    private static final ArrayList<Centroid> centroids = new ArrayList<>();

    public Kmeans(int numCluster) {
        euclidean = new Euclidean();
        mNumberOfCluster = numCluster;
    }

    public final void setTotalData(int total) {
        mTotalData = total;
    }

    public int getTotalData() {
        return mTotalData;
    }

    public static void initialize() {
        System.out.println("Centroids initialized at:");
        // calculate centroid with Euclidean distance
        centroids.add(new Centroid(2.0, 2.0)); // lowest set.
        centroids.add(new Centroid(2.0, 4.0)); // highest set.
//        centroids.add(new Centroid(3.0, 5.0));
        System.out.println("     (" + centroids.get(0).getX() + ", " + centroids.get(0).getY() + ")");
        System.out.println("     (" + centroids.get(1).getX() + ", " + centroids.get(1).getY() + ")");
//        System.out.println("     (" + centroids.get(2).getX() + ", " + centroids.get(2).getY() + ")");
        System.out.print("\n");
    }

    /**
     * Calculates the kMean
     */
    public void kMeanCluster() {
        final double bigNumber = Math.pow(10, 10);    // some big number that's sure to be larger than our data range.
        double minimum = bigNumber;                   // The minimum value to beat.
        double distance = 0.0;                        // The current minimum value.
        int sampleNumber = 0;
        int cluster = 0;
        boolean isStillMoving = true;
        Data newData = null;

        // Add in new data, one at a time, recalculating centroids with each new one.
        while (dataSet.size() < getTotalData()) {
            // newData = new Data(A, B);
            newData = new Data(this.sample[sampleNumber][0], this.sample[sampleNumber][1]);

            dataSet.add(newData);
            minimum = bigNumber;
            for (int i = 0; i < mNumberOfCluster; i++) {
                distance = euclidean.calculate(newData, centroids.get(i));
                if (distance < minimum) {
                    minimum = distance;
                    cluster = i;
                }
            }
            newData.setCluster(cluster);

            // calculate new centroids.
            for (int i = 0; i < mNumberOfCluster; i++) {
                int totalX = 0;
                int totalY = 0;
                int totalInCluster = 0;
                for (Data dataSet1 : dataSet) {
                    if (dataSet1.getCluster() == i) {
                        totalX += dataSet1.getX();
                        totalY += dataSet1.getY();
                        totalInCluster++;
                    }
                }
                if (totalInCluster > 0) {
                    centroids.get(i).setX(totalX / totalInCluster);
                    centroids.get(i).setY(totalY / totalInCluster);
                }
            }
            sampleNumber++;
        }

        // Now, keep shifting centroids until equilibrium occurs.
        while (isStillMoving) {
            // calculate new centroids.
            for (int i = 0; i < mNumberOfCluster; i++) {
                int totalX = 0;
                int totalY = 0;
                int totalInCluster = 0;
                for (Data dataSet1 : dataSet) {
                    if (dataSet1.getCluster() == i) {
                        totalX += dataSet1.getX();
                        totalY += dataSet1.getY();
                        totalInCluster++;
                    }
                }
                if (totalInCluster > 0) {
                    centroids.get(i).setX(totalX / totalInCluster);
                    centroids.get(i).setY(totalY / totalInCluster);
                }
            }

            // Assign all data to the new centroids
            isStillMoving = false;

            for (Data tempData : dataSet) {
                minimum = bigNumber;
                for (int j = 0; j < mNumberOfCluster; j++) {
                    distance = euclidean.calculate(tempData, centroids.get(j));
                    if (distance < minimum) {
                        minimum = distance;
                        cluster = j;
                    }
                }
                tempData.setCluster(cluster);
                if (tempData.getCluster() != cluster) {
                    tempData.setCluster(cluster);
                    isStillMoving = true;
                }
            }
        }
    }

    public void printDetails() {
        System.out.println("Printing details..........");
        // Print out clustering results.
        for (int i = 0; i < mNumberOfCluster; i++) {
            System.out.println("Cluster " + i + " includes:");
            for (int j = 0; j < getTotalData(); j++) {
                if (dataSet.get(j).getCluster() == i) {
                    System.out.println("     (" + dataSet.get(j).getX() + ", " + dataSet.get(j).getY() + ")");
                }
            } // j
            System.out.println();
        } // i

        // Print out centroid results.
        System.out.println("Centroids finalized at:");
        for (int i = 0; i < mNumberOfCluster; i++) {
            System.out.println("     (" + centroids.get(i).getX() + ", " + centroids.get(i).getY() + ")");
        }
        System.out.print("\n");
    }
}