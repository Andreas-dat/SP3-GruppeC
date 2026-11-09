import java.util.ArrayList;

public class Profile {

    private ArrayList<Media> planToWatch;
    private ArrayList<Media> hasWatched;
    private String nickname;
    private boolean isChild;

    public Profile(String nickname, boolean isChild) {
        this.nickname = nickname;
        this.isChild = isChild;
        planToWatch = new ArrayList<>();
        hasWatched = new ArrayList<>();
    }

    public void addToList(Media media) {
        //This method adds to a list. Should work for both adding to hasWatched and planToWatch
    }

    public void removeFromList() {
        //Removes selected media from list.
    }
}
