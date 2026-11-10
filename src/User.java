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

    public String getUsername() {
        return username;
    }

    public void changePassword() {
        //this method is resposible for chaning the password of current user. user should be loggedout afterwards.
        //Make sure that he password followes rules and that the user enters it twice
        //Should be logged out afterwards
    }

    public void changeUsername() {
        //Change usrename of currentuser. make sure it follows rules and it dosnt exsist Should also update the csv with profiles
        //log ouser out aftwewards
    }

    public void changeMail() {
        //Change email of current user, make sure it follows rules (valid email etc)
        //User should be logged out afterwards
    }
    public void loadProfiles(String nickname, ArrayList<Media> planToWatch, ArrayList<Media> hasWatched) {
        profiles.add(new Profile(nickname, planToWatch, hasWatched));

        //Load users profiles, list etc from corresponding csv documnet (Shuld happend on login)
    }
    private void changeProfile() {
        //Change current profile to another valid profile that exsists
    }

    private void changeNickname() {
        //change nickname of current profile on the user, check rules etc (Not the same name as another profile)

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

    public Profile chooseCurrentProfile() {
        displayProfiles();
            int i = TextUI.promptNumeric("Choose profile");
            try {
                return profiles.get(i - 1);
            } catch (IndexOutOfBoundsException e) {
                TextUI.displayMessage(String.valueOf(e));
                TextUI.displayMessage("invalid input\nShould be between 1-" + profiles.size());
                chooseCurrentProfile();
            }
            return null;
        }


    public void displayProfiles() {
        ArrayList<String> profile = new ArrayList<>();
        for(Profile p: profiles) {
            profile.add(p.getNickname());
        }
        TextUI.displayList(profile,"List of profiles", true);
    }

    public void setCurrentProfile() {
        this.currentProfile = chooseCurrentProfile();
    }

    public String getName () {
            return name;
        }

        public String getPassword () {
            return password;
        }

        public String getUsername () {
            return username;
        }

        public Profile getCurrentProfile () {
            return currentProfile;
        }

        public ArrayList<Profile> getProfiles () {
            return profiles;
        }

        public void addProfile () {
        }
    }

