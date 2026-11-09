import java.util.ArrayList;

public class Profile {

    private ArrayList<MediaContent> planToWatch;
    private ArrayList<MediaContent> hasWatched;
    private String nickname;

    public Profile(String nickname) {
        this.nickname = nickname;
    }

    public void addToList(MediaContent media) {
        //This method adds to a list. Should work for both adding to hasWatched and planToWatch
    }

    public void removeFromList() {
        //Removes selected media from list.
    }

    public ArrayList<MediaContent> getHasWatched() {
        return hasWatched;
    }

    public ArrayList<MediaContent> getPlanToWatch() {
        return planToWatch;
    }

    public void toggleWatchList(MediaContent media) {   //Adds media to watchlist if not already there. Otherwise, removes it
        if (planToWatch.contains(media)) {
            planToWatch.remove(media);
        } else {
            planToWatch.add(media);
        }
    }

    public void removeFromHasWatched(MediaContent media) {
        hasWatched.remove(media);
    }
}
