import java.util.ArrayList;

public class Load {
    public static Profile chooseProfile(ArrayList<Profile> profiles) {
        ArrayList<String> profile = new ArrayList<>();
        for(Profile p: profiles) {
            profile.add(p.getNickname());
        }
        TextUI.displayList(profile,"List of profiles", true);
       int i = TextUI.promptNumeric("Choose profile");
       try {
           return profiles.get(i - 1);
       } catch (IndexOutOfBoundsException e) {
           TextUI.displayMessage(String.valueOf(e));
           TextUI.displayMessage("invalid input\nShould be between 1-" + profiles.size());
           chooseProfile(profiles);
       }
       return null;
    }

    public static void loadProfiles(User user, ArrayList<Media> media) {
        ArrayList<String> profile = FileIO.readData("data/profiles/" + user.getName() + ".csv", true);

        for (String p : profile) {
            String[] s = p.split(";");
            String name = s[0];
            ArrayList<Media> planToWatch = convertStringToMedia(s[1], media);
            ArrayList<Media> hasWatched = convertStringToMedia(s[2], media);


            user.loadProfiles(name, planToWatch, hasWatched);
        }
    }



    private static ArrayList<Media> convertStringToMedia(String list, ArrayList<Media> mediaList) {
        ArrayList<Media> media = new ArrayList();
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

    private ArrayList<Media> loadMedia(String path, String type) {
        //load movies.csv and series from csv file, create objects and add to media arraylist
        ArrayList<String> mediaData = FileIO.readData(path, false);
        ArrayList<Media> media = new ArrayList<>();

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
        return media;
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

}
