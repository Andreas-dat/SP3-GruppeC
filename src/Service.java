import java.util.ArrayList;

public class Service {
    private static final int CURRENTYEAR = 2026;
    private static final int MINIMUMPASSWORDLENGTH = 8;

    private final String name;
    private User currentUser;
    private ArrayList<Media> currentMedia;
    private ArrayList<Media> familyMedia;
    private ArrayList<Media> media;
    private ArrayList<User> users;
    private ServiceState state;

    public Service(String name) {
        this.name = name;
        this.currentUser = null;
        this.users = Load.loadUsers();
        this.currentMedia = new ArrayList<>();
        this.media = new ArrayList<>();
        Load.loadMedia(media,"data/media/movies.csv", "movies");
        Load.loadMedia(media,"data/media/series.csv", "series");
        this.familyMedia = Search.familyMedia(media);
        state = ServiceState.START_MENU;
    }

    public void runService() {
        while (state != ServiceState.EXIT) {
            switch (state) {
                case START_MENU -> startMenu();
                case PROFILE_MENU -> profileMenu();
                case MAIN_MENU -> mainMenu();
                case SEARCH_MENU -> searchMenu();
            }
        }
    }

    public void startMenu() {
        int maxTries = 3;
        int choice = TextUI.promptMenuChoice(MenuOptions.startMenu(), "Welcome to " + name);

        switch(choice) {
            case 0 -> state = ServiceState.EXIT;
            case 1 -> userLogin(maxTries);
            case 2 -> registerUser();
        }
    }

    public void profileMenu() {
        if(currentUser.getProfiles().isEmpty()) {
            Load.loadProfiles(currentUser, currentMedia);
        }

        int profileCount = currentUser.getProfiles().size();

        int choice = TextUI.promptMenuChoice(MenuOptions.profileMenu(currentUser.getProfiles()), "Choose profile");
        if (choice == 0) {
            logout();
            return;
        }

        if (profileCount + 1 == choice && profileCount < 6) {
            currentUser.createNewProfile();
            return;
        }

        currentUser.setCurrentProfile(choice - 1);

        if (currentUser.getCurrentProfile().getIsChild()) {
            currentMedia = familyMedia;
        } else {
            currentMedia = media;
        }
        state = ServiceState.MAIN_MENU;
    }

    public void mainMenu() {
        int choice = TextUI.promptMenuChoice(MenuOptions.mainMenu(), "Main menu");

        switch(choice) {
            case 0 -> logout();
            case 1 -> displayMediaList(currentMedia);
            case 2 -> displayMediaList(currentUser.getCurrentProfile().getHasWatched());
            case 3 -> displayMediaList(currentUser.getCurrentProfile().getPlanToWatch());
            case 4 -> state = ServiceState.SEARCH_MENU;
            case 5 -> {
                currentUser.setCurrentProfileToNull();
                state = ServiceState.PROFILE_MENU;
            }
        }
    }

    private void watchableMediaMenu(Media media) {
        Profile profile = currentUser.getCurrentProfile();

        Watchable w = (Watchable)media;

        boolean running = true;
        while (running) {
            boolean isOnWatchlist = profile.getPlanToWatch().contains(media);
            boolean isOnHasWatched = profile.getHasWatched().contains(media);
            int choice = TextUI.promptMenuChoice(MenuOptions.mediaMenu(isOnWatchlist, isOnHasWatched), "Choose an option");
            switch (choice) {
                case 0 -> running = false;
                case 1 -> w.play(profile);
                case 2 -> currentUser.getCurrentProfile().toggleWatchList(media);
                case 3 -> {
                    if (isOnHasWatched) {
                        currentUser.getCurrentProfile().removeFromHasWatched(media);
                    } else {
                        running = false;
                    }
                }
            }
        }
    }

    public void searchMenu() {
        ArrayList<Media> filteredList = new ArrayList<>();

        int choice = TextUI.promptMenuChoice(MenuOptions.searchMenu(), "Search by:");
        switch (choice) {
            case 0 -> state = ServiceState.MAIN_MENU;
            case 1 -> filteredList = Search.searchByRating(currentMedia);
            case 2 -> filteredList = Search.searchByTitle(currentMedia);
            case 3 -> filteredList = Search.searchByYear(currentMedia);
            case 4 -> filteredList = Search.searchByDecade(currentMedia);
            case 5 -> filteredList = Search.searchByCategory(currentMedia);
        }
        displayMediaList(filteredList);
    }

    private void userLogin(int tries) {
        if (tries == 0) {
            TextUI.displayMessage("You have used your maximum amount of tries\nPlease try again later");
            return;
        }
        String username = TextUI.promptText("Enter username");
        String password = TextUI.promptText("Enter password");
        if (!checkUser(username, password)) {
            TextUI.displayMessage("Invalid Username or Password\nyou have " + tries + " tries left");
            userLogin(tries - 1);
            return;
        }
        TextUI.displayMessage("Successful login\n Welcome " + currentUser.getName());
        state = ServiceState.PROFILE_MENU;
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
        String name = TextUI.promptText("Please enter a name:");
        String eMail = getUserMail();
        int birthYear = getUserBirthYear();
        String username = getUserUsername();
        String password = getUserPassword();

        createUser(name, username, password, eMail, birthYear);
    }

    private void createUser(String name, String username, String password, String eMail, int birthYear) {
        User user = new User(name, username, password, eMail, birthYear);
        users.add(user);
        TextUI.displayMessage("User registered " + name);

        user.createStandardProfiles(name);
        Save.saveProfiles(user);
    }

    private void logout() {
        Save.saveUsers(users);
        Save.saveProfiles(currentUser);
        currentUser.setCurrentProfileToNull();
        currentUser = null;
        TextUI.displayMessage("You have logged out");
        state = ServiceState.START_MENU;
    }

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

    public void displayMediaList(ArrayList<Media> media) {
        ArrayList<String> mediaList = new ArrayList<>();
        for (Media m : media) {
            mediaList.add(m.toString());
        }
        mediaList.add("Main menu");

        int choice = TextUI.promptMenuChoice(mediaList, "Choose a movie or film");
        if (choice == 0) {
            return;
        }
        Media mediaChoice = media.get(choice - 1);
        watchableMediaMenu(mediaChoice);
    }
}
