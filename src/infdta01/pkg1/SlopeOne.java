package infdta01.pkg1;


import java.util.Map;
import java.util.HashMap;

public class SlopeOne {

    public UserPreference mUserPreference;
    public Map<Integer, Double> mItemARatings;
    public Map<Integer, Double> mItemBRatings;
    public int mTotalRatedBoth;

    public SlopeOne(UserPreference userPreference) {
        mUserPreference = userPreference;
        mItemARatings = new HashMap<>();
        mItemBRatings = new HashMap<>();
    }

    /**
     * Fills the hashmaps with the userId and Rating for users who rated both items
     *
     * @param itemA
     * @param itemB
     */
    private void fillMaps(int itemA, int itemB) {
        mTotalRatedBoth = 0;
        mItemARatings.clear();
        mItemBRatings.clear();
        for (Map.Entry<Integer, User> entry : mUserPreference.mUserPreference.entrySet()) {
            if (entry.getValue().bothRated(itemA, itemB)) {
                mItemARatings.put(entry.getValue().getUserId(), entry.getValue().getRating(itemA));
                mItemBRatings.put(entry.getValue().getUserId(), entry.getValue().getRating(itemB));
                mTotalRatedBoth++;
            }
        }
    }

    /**
     * Calculates the deviation between 2 items
     *
     * @param itemA
     * @param itemB
     * @return result
     */
    private double calcDeviationBetweenPairOfItems(int itemA, int itemB) {
        fillMaps(itemA, itemB);
        double result = 0.0;

        for (Map.Entry<Integer, Double> entry : mItemARatings.entrySet()) {
            result += (entry.getValue() - mItemBRatings.get(entry.getKey())) / mTotalRatedBoth;
        }
        return result;
    }

    /**
     * Calculates the rating prediction for userId
     *
     * @param userId
     * @param itemId
     * @return result
     */
    public double calculatePrediction(int userId, int itemId) {
        double numerator = 0.0;
        int denominator = 0;

        for (Map.Entry<Integer, Double> item : mUserPreference.mUserPreference.get(userId).ratedItems.entrySet()) {
            numerator += (calcDeviationBetweenPairOfItems(itemId, item.getKey()) + item.getValue()) * mTotalRatedBoth;
            denominator += mTotalRatedBoth;
        }
        return numerator / denominator;
    }
}
