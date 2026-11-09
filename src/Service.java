import javax.print.attribute.standard.Media;
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

    private void userLogin() {
        //prompts user for username and password and checks if correct and sets current user. otherwise prompt again
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

    public void displayMediaInfo(MediaContent media) {
        //method should display information about the selected movie/series.
        TextUI.displayMessage("Media information: ");
        TextUI.displayMessage(media.toString());        //Prints info about media. todo: Must make separate toString() depending on media.
    }


    public void displayMediaOptions(MediaContent media) { //give options: add/remove to watch list, play or
        ArrayList<String> options = new ArrayList<>();      //Liste som skal bruges til at vise menu
        ArrayList<MediaContent> currentWatchlist = currentUser.getCurrentProfile().getPlanToWatch();
        ArrayList<MediaContent> currentHasWatched = currentUser.getCurrentProfile().getHasWatched();
        boolean isOnWatchlist = currentWatchlist.contains(media);
        boolean isOnHasWatched = currentHasWatched.contains(media);

        options.add("Play media");
        if (isOnWatchlist) {     //If-statement checks if current profile contains the movie/series, user options vary on result
            options.add("Remove from watchlist");
        } else {
            options.add("Add to watchlist"); //todo: is watchlist an ok name when presented to the user?
        }

        if (isOnHasWatched) {       //only adds option if it already is on hasWatched
            options.add("Remove from watch history");       //todo: beslut om vi denne funktionalitet er nødvendig
        }

        int choice = TextUI.promptMenuChoice(options, "Choose one: ");  //Shows menu to user, prompts a choice.

        if (choice < 1 || choice > options.size()) {        //Checks if choice is valid.
            System.out.println("Invalid choice");
            return;     //todo: exception handling?
        }
        switch (choice) {
            case 1 -> media.play();       //plays media
            case 2 -> currentUser.getCurrentProfile().toggleWatchList(media);
            case 3 -> currentUser.getCurrentProfile().removeFromHasWatched(media);
            default -> TextUI.displayMessage("Invalid choice");
        }
    }

    public void displayMediaList() {
        //Method should show list based on criteria (or no criteria)
        ArrayList<String> mediaList = new ArrayList<>();
        //Should be able to choose a media from the list
        int choice = TextUI.promptMenuChoice(mediaList, "Choose a movie or film");
        MediaContent mediaChoice = mediaList.get(choice - 1);       //todo: fix data types so we can get correct media to display info about
        displayMediaInfo(mediaChoice);
    }
}
