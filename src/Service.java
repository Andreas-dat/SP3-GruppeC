import java.util.ArrayList;

public class Service {
    private static final int CURRENTYEAR = 2026;
    private static final int MINIMUMPASSWORDLENGTH = 8;

    private final String name;
    private User currentUser;
    private ArrayList<Media> media;
    private ArrayList<User> users;
    private ServiceState state;

    public Service(String name) {
        this.name = name;
        this.users = Load.loadUsers();
        this.media = new ArrayList<>();
    }

    public void startService() {
        Load.loadMedia(media,"data/media/movies.csv", "movies");
        Load.loadMedia(media,"data/media/series.csv", "series");
        state = ServiceState.START_MENU;

        //TEST
        currentUser = users.getFirst();
        state = ServiceState.PROFILE_MENU;
        //TEST

        runService();
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
            Load.loadProfiles(currentUser, media);
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
        state = ServiceState.MAIN_MENU;
    }

    public void mainMenu() {
        int choice = TextUI.promptMenuChoice(MenuOptions.mainMenu(), "Main menu");

        switch(choice) {
            case 0 -> logout();
            case 1 -> mediaListMenu(media);
            case 2 -> mediaListMenu(currentUser.getCurrentProfile().getHasWatched());
            case 3 -> mediaListMenu(currentUser.getCurrentProfile().getPlanToWatch());
            case 4 -> state = ServiceState.SEARCH_MENU;
            case 5 -> {
                currentUser.setCurrentProfileToNull();
                state = ServiceState.PROFILE_MENU;
            }
            case 6 -> currentUser.changeNickname();
            case 7 -> currentUser.changeChildStatus();
            case 8 -> {
                if(currentUser.deleteProfile()) logout();
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
            case 1 -> filteredList = Search.searchByRating(media);
            case 2 -> filteredList = Search.searchByTitle(media);
            case 3 -> filteredList = Search.searchByYear(media);
            case 4 -> filteredList = Search.searchByDecade(media);
            case 5 -> filteredList = Search.searchByCategory(media);
        }
        mediaListMenu(filteredList);
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
        ArrayList<Media> filteredList = new ArrayList<>();

        if (currentUser.getCurrentProfile().getIsChild()) {
            filteredList.addAll(Search.familyMedia(media));
        } else {
            filteredList.addAll(media);
        }

        for (Media m : filteredList) {
            mediaList.add(m.toString());
        }

        mediaList.add("Main menu");

        int choice = TextUI.promptMenuChoice(mediaList, "Choose a movie or film");
        if (choice == 0) {
            state = ServiceState.MAIN_MENU;
            return;
        }
        Media mediaChoice = media.get(choice - 1);
        watchableMediaMenu(mediaChoice);
    }
}
