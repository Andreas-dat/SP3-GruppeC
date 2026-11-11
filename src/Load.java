
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
        ArrayList<String> mediaData = FileIO.readData(path, false);

        for (String s : mediaData) {
            String[] values = s.split(";");
            String title = values[0];
            double rating = Double.parseDouble(values[3].replace(',', '.').trim());

            String[] releasePeriod = values[1].trim().split("-");
            int releaseYear = Integer.parseInt(releasePeriod[0].trim());

            ArrayList<Categories> categories = findCategories(values[2]);

            Media m = null;

            if (type.equals("movies")) {
                m = new Movie(title, categories, releaseYear,rating);
            }

            if (type.equals("series")) {
                int endOfRelease = findEndOfRelease(values[1]);

                ArrayList<Integer> episodesInSeason = findEpisodesInSeason(values[4]);

                m = new Series(title, categories, rating, releaseYear, endOfRelease, episodesInSeason);
            }
            if (m != null) {
                media.add(m);
            }
        }

    }

    private static ArrayList<Categories> findCategories(String line) {
        ArrayList<Categories> categories = new ArrayList<>();
        String[] values = line.split(",");
        for(String s : values) {
            for(Categories c : Categories.values()) {
                if(c.getCategory().equalsIgnoreCase(s.trim())) {
                    categories.add(c);
                }
            }
        }
        return categories;
    }

    private static int findEndOfRelease(String line) {
        String[] releasePeriod = line.trim().split("-");
        int endOfRelease;
        if (!line.contains("-")) {
            endOfRelease = Integer.parseInt(releasePeriod[0]);
        } else if (releasePeriod.length < 2) {
            endOfRelease = -1;
        } else {
            endOfRelease = Integer.parseInt(releasePeriod[1].trim());
        }
        return endOfRelease;
    }

    private static ArrayList<Integer> findEpisodesInSeason(String line) {
        ArrayList<Integer> episodesInSeason = new ArrayList<>();
        String[] seasonsAndEpisodes = (line.split(","));
        for (String s : seasonsAndEpisodes) {
            String[] seasonAndEpisode = (s.split("-"));
            episodesInSeason.add(Integer.parseInt(seasonAndEpisode[1]));
        }
        return episodesInSeason;
    }

    public static ArrayList<User> loadUsers() {
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
