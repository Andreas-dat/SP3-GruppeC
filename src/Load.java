
import java.util.ArrayList;

public class Load {

    public static void loadProfiles(User user, ArrayList<Media> media) {
        ArrayList<String> profile = FileIO.readData("data/profiles/" + user.getUsername() + ".csv", true);

        for (String p : profile) {
            ArrayList<Media> planToWatch = new ArrayList<>();
            ArrayList<Media> hasWatched = new ArrayList<>();

            String[] s = p.split(";");
            String name = s[0];
            boolean isChild = Boolean.parseBoolean(s[1].trim());
            try {
                planToWatch = convertStringToMedia(s[2], media);
            }catch (NumberFormatException e) {
            }
            try {
                hasWatched = convertStringToMedia(s[3], media);
            } catch (NumberFormatException e) {
            }

            user.createProfile(name, planToWatch, hasWatched, isChild);
        }
    }



    private static ArrayList<Media> convertStringToMedia(String list, ArrayList<Media> mediaList) {
        ArrayList<Media> media = new ArrayList<>();
        String[] s = list.split(",");

        for(String l : s) {
            int i = Integer.parseInt(l.trim());

            for(Media m: mediaList) {
                if(m.getID() == i) {
                    media.add(m);
                    break;
                }
            }
        }
        return media;
    }

    public static void loadMedia(ArrayList<Media> media, String path, String type) {
        //load movies.csv and series from csv file, create objects and add to media arraylist
        ArrayList<String> mediaData = FileIO.readData(path, false);

        if (type.equals("movies")) {

            for (String s : mediaData) {
                String[] values = s.split(";");
                String title = values[0];
                int releaseYear = Integer.parseInt(values[1].trim());
                ArrayList<Categories> movieGenre = new ArrayList<>();
                String[] d = values[2].split(",");
                for(String v : d) {
                    for(Categories c : Categories.values()) {
                        if(c.getCategory().equalsIgnoreCase(v.trim())) {
                            movieGenre.add(c);
                        }
                    }

                }
                double rating = Double.parseDouble(values[3].replace(',', '.').trim());

                //missing categories

                Media m = new Movie(title, movieGenre, releaseYear,rating);
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
                ArrayList<Categories> movieGenre = new ArrayList<>();
                String[] d = values[2].split(",");
                for(String v : d) {
                    for (Categories c : Categories.values()) {
                        if (c.getCategory().equalsIgnoreCase(v.trim()))
                        {
                            movieGenre.add(c);
                        }
                    }
                }
                Media m = new Series(title, movieGenre,  rating, releaseYear, endOfRelease, episodesInSeason);
                media.add(m);
            }
        }
    }

    public static ArrayList<User> loadUsers() {
        //load users from csv file, create users and add to users arraylist
        ArrayList<String> userData = FileIO.readData("data/users/users.csv", true);
        ArrayList<User> users = new ArrayList<>();

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
        return users;
    }

}
