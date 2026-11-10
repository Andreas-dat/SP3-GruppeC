import java.util.ArrayList;

public class Service {
    private final String name;
    private User currentUser;
    private ArrayList<Media> media;
    private ArrayList<User> users;

    public Service(String name) {
        this.name = name;
        this.currentUser = null;
        this.users = Load.loadUsers();
        this.media = new ArrayList<>();
        Load.loadMedia(media,"data/media/movies.csv", "movies");
        Load.loadMedia(media,"data/media/series.csv", "series");
    }

    public void startService() {


        int maxTries = 3;
        userLogin(maxTries);
        System.out.println(currentUser.getName());
        Load.loadProfiles(currentUser, media);

        currentUser.setCurrentProfile();


        for(Media m : currentUser.getCurrentProfile().getHasWatched()) {
            System.out.println(m);
        }

                //loadData()
        //startMenu()


    }



    public void startMenu() {
        TextUI.displayMessage("Welcome to !" + name + "\nYou have the following options");
        TextUI.displayMessage("");
//1 for login
        //2 register new user

        //prompts user for a number to choose login or register user
        //userLogin() or registerUser()
    }

    private boolean userLogin(int tries) {
        if (tries == 0) {
            TextUI.displayMessage("You have used your maximum amount of tries\nPlease try again later");
            return false;
        }
        String username = TextUI.promptText("Enter username");
        String password = TextUI.promptText("Enter password");
        if (checkUser(username, password)) {
            TextUI.displayMessage("Successful login\n Welcome " + currentUser.getName());
            return true;
        }
        TextUI.displayMessage("Invalid Username or Password\nyou have " + tries + " tries left");
        userLogin(tries - 1);
        return false;
        //prompts user for username and password and checks if correct and sets current user. otherwise prompt again
    }

    private boolean checkUser(String username, String password) {
        for(User u: users) {
            if(u.getUsername().equals(username) && u.getPassword().equals(password)) {
                setCurrentUser(u);
               return true;
            }
        }
        return false;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void registerUser() {
        //prompts user for name, username... check if valid
        //after successfully registering: startMenu()
    }

    private void createUser() {
        //create user object with parameters from method
        //add parameters
    }



    private void logout() {
        //set currentUser to null
    }

    public ArrayList<Media> getMedia() {
        return media;
    }
}
