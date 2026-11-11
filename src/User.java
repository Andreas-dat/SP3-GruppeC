import java.util.ArrayList;

public class User {
    private ArrayList<Profile> profiles;
    private Profile currentProfile;
    private String name;
    private String username;
    private String password;
    private String email;
    private int birthYear;

    public User(String name, String username, String password, String email, int birthYear) {
        this.profiles = new ArrayList<>();
        this.currentProfile = null;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthYear = birthYear;
    }

    public void createProfile(String nickname, ArrayList<Media> planToWatch, ArrayList<Media> hasWatched, boolean isChild) {
        profiles.add(new Profile(nickname, planToWatch, hasWatched, isChild));

    }

    private void changeNickname() {
        String nickname = TextUI.promptText("Change the name of " + currentProfile.getNickname() + "\n To:");
        this.currentProfile.setNickname(nickname);

    }

    public void changeChildStatus() {
        //Change the childstatus of current profile, can change both back anad forth.
    }

    public void deleteProfile() {
        //Delete current profile. remmeber to delete it from the arraylist of profiles
    }

    public void createProfile(String name, boolean isChild) {
        Profile profile = new Profile(name, isChild);

        profiles.add(profile);
    }

    public void createNewProfile() {
       String name = TextUI.promptText("Enter your nickname");
       boolean isChild = TextUI.promptBinary("Are you a child? Y/n");

       profiles.add(new Profile(name, isChild));
    }

    public void setCurrentProfile(int i) {
        this.currentProfile = profiles.get(i);
    }

    public String getName () {
        return name;
    }

    public String getPassword () {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public Profile getCurrentProfile () {
        return currentProfile;
    }

    public ArrayList<Profile> getProfiles () {
        return profiles;
    }

    public void setCurrentProfileToNull() {
        this.currentProfile = null;
    }

    public String toCSV() {
        return name + ", " + username + ", " + password + ", " + email + ", " + birthYear;
    }
}

