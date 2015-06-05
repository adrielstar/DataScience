package infdta01.pkg1;


import java.util.*;

public class NearestNeighbour {

    HashMap<User, Double> mNeighbours;
    Distance mDistance;
    String mCalcDistanceMethod;
    UserPreference mUserPreference;
    User mUser;
    int mItemId;
    double mThreshold;
    int mKNeighbours;
    double mProjectedRating;

    public NearestNeighbour(UserPreference up, int userId, int itemId, double threshold, int k, String calcDistanceMethod) {
        mNeighbours = new HashMap<>();
        mUserPreference = up;
        mUser = up.getUser(userId);
        mItemId = itemId;
        mThreshold = threshold;
        mKNeighbours = k;
        mCalcDistanceMethod = calcDistanceMethod;
        mDistance = new Distance();
        mProjectedRating = 0.0;
    }

    /**
     * Create a list of the nearest neighbours according to the settings passed at the constructor
     *
     * @return mNeighbours
     */
    public HashMap NearestNeighbours(String calculationMethod) {
        if (!mUser.isRated(mItemId)) {
            return null;
        }

        for (Map.Entry<Integer, User> entry : mUserPreference.mUserPreference.entrySet()) {
            if (entry.getValue().isRated(mItemId)) {
                double sim = mDistance.getDistance(mUserPreference, mUser.getUserId(), entry.getValue().getUserId(), calculationMethod);
                if (sim > mThreshold) {
                    if (mNeighbours.size() < mKNeighbours) {
                        mNeighbours.put(entry.getValue(), sim);
                    } else {
                        double lowestSim = Collections.min(mNeighbours.values());
                        if (sim > lowestSim) {
                            boolean found = false;
                            Set set = mNeighbours.entrySet();
                            Iterator iterator = set.iterator();
                            while (iterator.hasNext() && found == false) {
                                Map.Entry me = (Map.Entry) iterator.next();
                                if ((double) me.getValue() == lowestSim) {
                                    mNeighbours.remove(me.getKey());
                                    found = true;
                                }
                            }
                            mNeighbours.put(entry.getValue(), sim);
                        }
                    }
                }

            }
        }
        return mNeighbours;
    }

    /**
     * Predict a rating that a user will rate for a specific product based on what similar users has rated that product
     *
     * @return mProjectedRating
     */
    public double projectRating() {
        NearestNeighbours(mCalcDistanceMethod);

        double mProjectedRating = 0.0;
        double dist = 0.0;

        for (Map.Entry<User, Double> entry : mNeighbours.entrySet()) {
            dist += mDistance.getDistance(mUserPreference, mUser.getUserId(), entry.getKey().getUserId(), mCalcDistanceMethod);
        }

        for (Map.Entry<User, Double> entry : mNeighbours.entrySet()) {
            mProjectedRating += entry.getKey().getRating(mItemId) * (mDistance.getDistance(mUserPreference, mUser.getUserId(), entry.getKey().getUserId(), mCalcDistanceMethod)/dist);
        }
        return mProjectedRating;
    }

    public void printProjectedRating() {
        System.out.println("Projected rating: " + mProjectedRating);
    }
}
