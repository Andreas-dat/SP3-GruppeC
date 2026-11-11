import java.util.ArrayList;

public class MenuOptions {
    private static ArrayList<String> menu = new ArrayList();

    public static ArrayList<String> mainMenu() {
        menu.clear();
        menu.add("View all media");
        menu.add("View history");
        menu.add("View watchlist");
        menu.add("Search");
        menu.add("Change profile");
        menu.add("Change Nickname of profile");
        menu.add("Change child status");
        menu.add("Delete current propfile");
        menu.add("Logout");
        return menu;
    }

    public static ArrayList<String> startMenu() {
        menu.clear();
        menu.add("Login");
        menu.add("Register User");
        menu.add("Exit");

        return menu;
    }

    public static ArrayList<String> profileMenu(ArrayList<Profile> profiles) {
        menu.clear();
        for(Profile p: profiles) {
            menu.add(p.getNickname());
        }
        if(profiles.size() < 6) {
            menu.add("Create profile");
        }
        //TODO: Edit profile?
        menu.add("Logout");
        return menu;
    }

    public static ArrayList<String> mediaMenu(boolean isOnWatchlist, boolean isOnHasWatched) {
        menu.clear();
        menu.add("Play media");

        if (isOnWatchlist) {     //If-statement checks if current profile contains the movie/series, user options vary on result
            menu.add("Remove from watchlist");
        } else {
            menu.add("Add to watchlist"); //todo: is watchlist an ok name when presented to the user?
        }
        if (isOnHasWatched) {       //only adds option if it already is on hasWatched
            menu.add("Remove from watch history");       //todo: beslut om vi denne funktionalitet er nødvendig
        }

        menu.add("Main menu");

        return menu;
    }

    public static ArrayList<String> searchMenu () {
        menu.clear();
        menu.add("Rating");
        menu.add("Title");
        menu.add("Year");
        menu.add("Decade");
        menu.add("Category");

        menu.add("Main menu");

        return menu;
    }


}
