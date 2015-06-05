package infdta01.pkg1;

import java.util.Map;
import java.util.TreeMap;

public class User {

    public final int userId;
    public final Map<Integer, Double> ratedItems;

    public User(int userId) {
        this.ratedItems = new TreeMap<>();
        this.userId = userId;
    }

    /**
     * Set the item id and rating
     *
     * @param itemId
     * @param rating
     */
    public void setItemAndRating(int itemId, double rating) {
        this.ratedItems.put(itemId, rating);
    }

    public int getUserId() {
        return this.userId;
    }

    public TreeMap<Integer, Double> getRatedItems() {
        return (TreeMap<Integer, Double>) this.ratedItems;
    }

    public void printRatedItems() {
        System.out.println("User id: " + this.userId);
        for (Map.Entry<Integer, Double> item : this.ratedItems.entrySet()) {
            System.out.println("Key: " + item.getKey() + " Value: " + item.getValue());
        }
    }
    
    /**
     * Get the rating of item
     *
     * @param itemId
     * @return 
     */
    public double getRating(int itemId) {
       return ratedItems.get(itemId);
    }

    /**
     * Check if user has rated a specific item
     *
     * @param itemId
     * @return
     */
    public boolean isRated(int itemId) {
        for (Map.Entry<Integer, Double> entry : ratedItems.entrySet()) {
            if (entry.getKey() == itemId) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if both items are rated
     *
     * @param itemA
     * @param itemB
     * @return boolean
     */
    public boolean bothRated(int itemA, int itemB) {
        if (isRated(itemA) && isRated(itemB)) {
            return true;
        }
        return false;
    }
}
