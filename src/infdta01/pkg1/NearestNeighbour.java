package infdta01.pkg1;

import java.util.Map;
import java.util.ArrayList;

public class NearestNeighbour {
    // change arraylist into hasmap <rating, pearson>
    public ArrayList mNeighbours;
    Pearson mPearsonCalculation;
    UserPreference mUserPreference;
    User mUser;
    int mItemId;
    double mThreshold;
    int mKNeighbours;

    public NearestNeighbour(UserPreference userPreference, User user, int itemId, double threshold, int k) {
        mPearsonCalculation = new Pearson();
        mNeighbours = new ArrayList<User>();
        mUserPreference = userPreference;
        mUser = user;
        mItemId = itemId;
        mThreshold = threshold;
        mKNeighbours = k;
    }

    public ArrayList NearestNeighbours() {
        for (Map.Entry<Integer, User> entry : mUserPreference.mUserPreference.entrySet()) {
            if (entry.getValue().isRated(mItemId)) {
                double pearson = mPearsonCalculation.calcPearson(entry.getValue().ratedItems, mUser.ratedItems);
                if (pearson > mThreshold) {
                    if (mNeighbours.size() < mKNeighbours) {
                        mNeighbours.add(entry.getValue());
                    } else {
                        // delete lowest pearson in the mKneighbours
                    }
                }
            }
        }
        return mNeighbours;
    }

    public double projectedRating() {
        double result = 0.0;
        return result;
    }
}
