import java.util.ArrayList;

public class UserService {
    private static User current = null;
    private static final int CURRENTYEAR = 2026;
    private static final int MINIMUMPASSWORDLENGTH = 8;


    public static User userLogin(int tries, ArrayList<User> users) {
        if (tries == 0) {
            TextUI.displayMessage("You have used your maximum amount of tries\nPlease try again later");
            return null;
        }
        String username = TextUI.promptText("Enter username");
        String password = TextUI.promptText("Enter password");

        if (!checkUser(username, password, users)) {
            TextUI.displayMessage("Invalid Username or Password\nyou have " + (tries - 1) + " tries left");
            userLogin(tries - 1, users);
        } else {
            TextUI.displayMessage("Successful login\n Welcome " + username);
        }
        return current;

    }

    private static boolean checkUser(String username, String password, ArrayList<User> users) {
        for(User u: users) {
            if(u.getUsername().equals(username) && u.getPassword().equals(password)) {
                current = u;
                return true;
            }
        }
        return false;
    }
    public static void registerUser(ArrayList<User> users) {
        String name = TextUI.promptText("Please enter a name:");
        String eMail = getUserMail();
        int birthYear = getUserBirthYear();
        String username = getUserUsername(users);
        String password = getUserPassword();

        createUser(name, username, password, eMail, birthYear, users);

    }

    private static void createUser(String name, String username, String password, String eMail, int birthYear, ArrayList<User> users) {
        User user = new User(name, username, password, eMail, birthYear);
        users.add(user);
        TextUI.displayMessage("User registered " + name);

        user.createStandardProfiles(username);
        SaveManager.saveProfiles(user);
    }
    private static String getUserMail(){
        String eMail = TextUI.promptText("Please enter your Email:"); //check if mail has characters '@' and '.'
        if (eMail.indexOf('@') == -1 || eMail.indexOf('.') == -1){
            getUserMail();
        }
        return eMail;
    }

    private static int getUserBirthYear(){
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
    private static String getUserUsername(ArrayList<User> users){
        String username = TextUI.promptText("Please enter a username:"); //run through list of existing usernames, make sure username is not duplicate
        for (User user : users){
            if ((user.getUsername()).equals(username)){
                getUserUsername(users);
            }
        }
        return username;
    }

    private static String getUserPassword(){
        String password = TextUI.promptText("Please enter a password:"); //check if length is valid
        if (password.length() < MINIMUMPASSWORDLENGTH){
            getUserPassword();
        }
        return password;
    }

}
