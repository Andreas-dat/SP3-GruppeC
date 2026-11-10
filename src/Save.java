import java.util.ArrayList;

public class Save {

    public static void saveUsers(ArrayList<User> users) {
        ArrayList<String> usersData = new ArrayList<>();

        for (User u : users) {
            usersData.add(u.toCSV());
        }

        FileIO.saveData(usersData, "data/users/users.csv", "Name, Username, Password, Email, BirthYear");

        TextUI.displayMessage("Users saved to csv");
    }

    public static void saveProfiles(User user) {
        ArrayList<Profile> profiles = user.getProfiles();
        ArrayList<String> profilesData = new ArrayList<>();

        for (Profile p : profiles) {
            profilesData.add(p.toCSV());
        }

        FileIO.saveData(profilesData, "data/profiles/" + user.getUsername() + ".csv", "Nickname; PlanToWatch; HasWatched; IsChild");

        TextUI.displayMessage("Users saved to csv");

    }
}
