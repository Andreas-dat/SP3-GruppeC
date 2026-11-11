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
    private boolean running;

    public Service(String name) {
        this.name = name;
        this.currentUser = null;
        this.users = Load.loadUsers();
        this.currentMedia = new ArrayList<>();
        this.media = new ArrayList<>();
        Load.loadMedia(media,"data/media/movies.csv", "movies");
        Load.loadMedia(media,"data/media/series.csv", "series");
        this.familyMedia = Search.familyMedia(media);
    }

    public void runService() {
        running = true;

        while (running) {
            while (currentUser == null && running) {
                startMenu();
            }
            if (running && currentUser.getCurrentProfile() == null) {
                profileMenu();
            }
            if(running && currentUser != null) {
                mainMenu();
            }
        }
    }

    public void profileMenu() {
        if(currentUser.getProfiles().isEmpty()) {
            Load.loadProfiles(currentUser, media);
        }

        int profileCount = currentUser.getProfiles().size();

        int choice = TextUI.promptMenuChoice(MenuOptions.profileMenu(currentUser.getProfiles()), "Choose profile");
        if (choice == 0) {
            logout();
            return;
        }
        if(profileCount >= choice) {
            if(currentUser.getProfiles().get(choice - 1).getIsChild()) {
                currentMedia = familyMedia;

            } else {
                currentMedia = media;
            }
            currentUser.setCurrentProfile(choice - 1);
        }
        else if(profileCount + 1 == choice && profileCount < 6) {
            currentUser.createNewProfile();
            profileMenu();
        }

    }

    public void startMenu() {
       int maxTries = 3;
       int choice = TextUI.promptMenuChoice(MenuOptions.startMenu(), "Welcome to");

       switch(choice) {
           case 0 -> running = false;
           case 1 -> setCurrentUser(UserService.userLogin(maxTries, users));
           case 2 -> UserService.registerUser(users);
       }
    }

    public void mainMenu() {
        int choice = TextUI.promptMenuChoice(MenuOptions.mainMenu(), "Main menu");

        switch(choice) {
            case 0 -> logout();
            case 1 -> displayMediaList(currentMedia);
            case 2 -> displayMediaList(currentUser.getCurrentProfile().getHasWatched());
            case 3 -> displayMediaList(currentUser.getCurrentProfile().getPlanToWatch());
            case 4 -> searchMenu();
            case 5 -> {
                currentUser.setCurrentProfileToNull();
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
            case 0 -> {
                return;
            }
            case 1 -> filteredList = Search.searchByRating(currentMedia);
            case 2 -> filteredList = Search.searchByTitle(currentMedia);
            case 3 -> filteredList = Search.searchByYear(currentMedia);
            case 4 -> filteredList = Search.searchByDecade(currentMedia);
            case 5 -> filteredList = Search.searchByCategory(currentMedia);
        }
        displayMediaList(filteredList);
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    private void logout() {
        Save.saveUsers(users);
        Save.saveProfiles(currentUser);
        currentUser.setCurrentProfileToNull();
        currentUser = null;
    }

    public void displayMediaList(ArrayList<Media> media) {
        ArrayList<String> mediaList = new ArrayList<>();
        for (Media m : media) {
            mediaList.add(m.toString());
        }
        mediaList.add("Main menu");
        //Method should show list based on criteria (or no criteria)
        //Should be able to choose a media from the list
        int choice = TextUI.promptMenuChoice(mediaList, "Choose a movie or film");
        if (choice == 0) {
            return;
        }
        Media mediaChoice = media.get(choice - 1);
        watchableMediaMenu(mediaChoice);
    }
}
