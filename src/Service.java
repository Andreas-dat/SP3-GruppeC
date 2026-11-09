import jdk.jfr.Category;

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
        loadData();
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





}
