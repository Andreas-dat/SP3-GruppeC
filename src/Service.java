import java.util.ArrayList;

public class Service {
    private final String name;
    private User currentUser;
    private ArrayList<Media> media;
    private ArrayList<User> users;

    public Service(String name) {
        this.name = name;
        this.media = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public void startService() {
        //loadData()
        //startMenu()
    }

    public void startMenu() {
        //prompts user for a number to choose login or register user
        //userLogin() or registerUser()
    }

    private void userLogin(int tries) {
        if (tries >= 0) {
            String username = TextUI.promptText("Enter username");
            String password = TextUI.promptText("Enter password");
            if (checkUser(username, password)) {
                currentUser = setUser;
                return;
            }
            TextUI.displayMessage("Invalid Username or Password\nyou have " + tries + " tries left");
            userLogin(tries - 1);
            //prompts user for username and password and checks if correct and sets current user. otherwise prompt again
        }
        TextUI.displayMessage("You have used your maximum amount of tries\nPlease try again later");
    }

    private boolean checkUser(String username, String password) {
        for(User u: users) {
            if(u.getUserName.equals(username) && u.getPassword.equals(password)) {
               return true;
            }
        }
        return false;

    }


    private void registerUser() {
        //prompts user for name, username... check if valid
        //after successfully registering: startMenu()
    }

    private void createUser() {
        //create user object with parameters from method
        //add parameters
    }

    private void loadData() {
        //loadMedia()
        //loadUsers()
    }

    private void loadMedia() {
        //load movies and series from csv file, create objects and add to media arraylist
    }

    private void loadUsers() {
        //load users from csv file, create users and add to users arraylist
    }

    private void logout() {
        //set currentUser to null
    }





}
