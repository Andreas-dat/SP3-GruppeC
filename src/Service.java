import java.util.ArrayList;

public class Service {
    private final String name;
    private User currentUser;
    private ArrayList<Media> media;
    private ArrayList<User> users;

    public Service(String name) {
        this.name = name;
        this.media = new ArrayList<>();
        this.currentUser = null;
        this.users = new ArrayList<>();
    }

    public void startService() {

        loadData();

        int maxTries = 3;
        userLogin(maxTries);
        System.out.println(currentUser.getName());
        Load.loadProfiles(currentUser, media);
        if(!currentUser.getProfiles().isEmpty()) {
            currentUser.chooseCurrentProfile();
        }

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

    private void loadData() {
        loadMedia("data/media/movies.csv", "movies");
        loadMedia("data/media/series.csv", "series");
        loadUsers("data/users/users.csv");

    }

    private void loadMedia(String path, String type) {
        //load movies.csv and series from csv file, create objects and add to media arraylist
        ArrayList<String> mediaData = FileIO.readData(path, false);

        if (type.equals("movies")) {

            for (String s : mediaData) {
                String[] values = s.split(";");
                String title = values[0];
                int releaseYear = Integer.parseInt(values[1].trim());
                double rating = Double.parseDouble(values[3].replace(',', '.').trim());

                //missing categories

                Media m = new Movie(title, rating, releaseYear);
                media.add(m);
            }
        }

        if (type.equals("series")) {
            for (String s : mediaData) {
                String[] values = s.split(";");
                String title = values[0];
                values[1] = values[1].trim();
                double rating = Double.parseDouble(values[3].replace(',', '.').trim());

                String[] releasePeriod = values[1].split("-");
                int releaseYear = Integer.parseInt(releasePeriod[0].trim());
                int endOfRelease;

                if (!values[1].contains("-")) {
                    endOfRelease = releaseYear;
                } else if (releasePeriod.length < 2) {
                    endOfRelease = -1;
                } else {
                    endOfRelease = Integer.parseInt(releasePeriod[1].trim());
                }

                //missing categories

                String[] seasonsAndEpisodes = (values[4].split(","));
                ArrayList<Integer> episodesInSeason = new ArrayList<>();

                for (String j : seasonsAndEpisodes) {
                    String[] seasonAndEpisode = (j.split("-"));
                    episodesInSeason.add(Integer.parseInt(seasonAndEpisode[1]));
                }

                Media m = new Series(title, rating, releaseYear, endOfRelease, episodesInSeason);
                media.add(m);
            }
        }
    }

    private void loadUsers(String path) {
        //load users from csv file, create users and add to users arraylist
        ArrayList<String> userData = FileIO.readData(path, true);

        for (String s : userData) {
            String[] values = s.split(",");
            String name = values[0].trim();
            String username = values[1].trim();
            String password = values[2].trim();
            String email = values[3].trim();
            int birthYear = Integer.parseInt(values[4].trim());

            User u = new User(name, username, password, email, birthYear);
            users.add(u);
        }
    }

    private void logout() {
        //set currentUser to null
    }

    public ArrayList<Media> getMedia() {
        return media;
    }
}
