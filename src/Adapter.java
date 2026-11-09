import java.util.ArrayList;

public class Adapter {
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
        ArrayList<String> profile = FileIO.readData("data/profiles/" + user.getName() + ".csv");

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

}
