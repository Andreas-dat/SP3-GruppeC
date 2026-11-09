import java.util.ArrayList;

public class Service {
    private static final int CURRENTYEAR = 2026;
    private static final int MINIMUMPASSWORDLENGTH = 8;

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

    private void userLogin() {
        //prompts user for username and password and checks if correct and sets current user. otherwise prompt again
    }

    private void registerUser() {
        //prompts user for name, username... check if valid
        //after successfully registering: startMenu()
        String name = TextUI.promptText("Please enter a name:");

        String eMail = TextUI.promptText("Please enter your Email:"); //check if mail has characters '@' and '.'
        if (eMail.indexOf('@') == -1 || eMail.indexOf('.') == -1){
            eMail = TextUI.promptText("Invalid email, try again:");
        }

        int birthYear = 0;
        try {
            birthYear = TextUI.promptNumeric("Please enter your birth year:");
            if (birthYear < 1920 || birthYear > CURRENTYEAR){
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e){
            TextUI.displayMessage("Please enter a valid number");
            birthYear = TextUI.promptNumeric("Please enter your birth year:");
        }

        String username = TextUI.promptText("Please enter a username:"); //run through list of existing usernames, make sure username is not duplicate
        for (User user : users){
            if ((user.getUsername()).equals(username)){
                username = TextUI.promptText("Username already taken, please enter another username:");
            }
        }

        String password = TextUI.promptText("Please enter a password:"); //check if length is valid
        if (password.length() < MINIMUMPASSWORDLENGTH){
            password = TextUI.promptText("Password must be 8 characters minimum. Please enter a password:");
        }

        createUser(name, username, password, eMail, birthYear);
    }

    private void createUser(String name, String username, String password, String eMail, int birthYear) {
        //create user object with parameters from method
        //add parameters
        User user = new User(name, username, password, eMail, birthYear);
        users.add(user);
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
