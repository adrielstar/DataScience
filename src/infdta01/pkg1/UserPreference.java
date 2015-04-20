package infdta01.pkg1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class UserPreference {

    public TreeMap<Integer, User> mUserPreference;

    public UserPreference(String location) throws FileNotFoundException {
        mUserPreference = new TreeMap<>();
        this.fillData(location);
    }

    public final void fillData(String location) throws FileNotFoundException {
        String fileName = location;
        File file = new File(fileName);
        Scanner read = new Scanner(file);

        while (read.hasNextLine()) {
            String line = read.nextLine();
            String[] tokens = line.split(",", -1);
//            String[] tokens = line.split("\t", -1);

            int aUserId = Integer.parseInt(tokens[0]);
            int aItemId = Integer.parseInt(tokens[1]);
            double aRating = Double.parseDouble(tokens[2]);

            this.addUserAndInfo(aUserId, aItemId, aRating);
        }
    }

    public void addDetails(User user, int itemId, double rating) {
        user.setItemAndRating(itemId, rating);
    }

    public void addUserAndInfo(int userId, int itemId, double rating) {
        if (mUserPreference.containsKey(userId)) {
            mUserPreference.get(userId).setItemAndRating(itemId, rating);
            return;
        }

        User user = new User(userId);
        mUserPreference.put(userId, user);
        this.addDetails(user, itemId, rating);
    }

    public void printUsers() {
        System.out.println("UserList size " + mUserPreference.size());
        for (Map.Entry<Integer, User> entry : mUserPreference.entrySet()) {
            System.out.println("index: " + entry.getKey() + " User Id: " + entry.getValue().getUserId());
        }
    }

    public User getUser(int userId) {
        return mUserPreference.get(userId);
    }
}
