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
        String name = TextUI.promptText("Please enter a name:");
        String eMail = getUserMail();
        int birthYear = getUserBirthYear();
        String username = getUserUsername();
        String password = getUserPassword();

        createUser(name, username, password, eMail, birthYear);

        userLogin();
    }

    private void createUser(String name, String username, String password, String eMail, int birthYear) {
        //create user object with parameters from method
        //add parameters
        User user = new User(name, username, password, eMail, birthYear);
        users.add(user);

        //standard profiles
        user.createProfile(name, false);
        user.createProfile("Kids", true);
    }



    private void logout() {
        //set currentUser to null
    }

    //Getters for register User

    private String getUserMail(){
        String eMail = TextUI.promptText("Please enter your Email:"); //check if mail has characters '@' and '.'
        if (eMail.indexOf('@') == -1 || eMail.indexOf('.') == -1){
            getUserMail();
        }
        return eMail;
    }

    private int getUserBirthYear(){
        int birthYear = 0;
        try {
            birthYear = TextUI.promptNumeric("Please enter your birth year:");
            if (birthYear < 1920 || birthYear > CURRENTYEAR){
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e){
            TextUI.displayMessage("Please enter a valid number");
            getUserBirthYear();
        }
        return birthYear;
    }
    private String getUserUsername(){
        String username = TextUI.promptText("Please enter a username:"); //run through list of existing usernames, make sure username is not duplicate
        for (User user : users){
            if ((user.getUsername()).equals(username)){
                getUserUsername();
            }
        }
        return username;
    }

    private String getUserPassword(){
        String password = TextUI.promptText("Please enter a password:"); //check if length is valid
        if (password.length() < MINIMUMPASSWORDLENGTH){
            getUserPassword();
        }
        return password;
    }





}
