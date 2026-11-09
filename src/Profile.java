import java.util.ArrayList;

public class Profile {

    private ArrayList<Media> planToWatch;
    private ArrayList<Media> hasWatched;
    private String nickname;

    public Profile(String nickname) {
        this.nickname = nickname;
    }

    public void addToList(Media media) {
        //This method adds to a list. Should work for both adding to hasWatched and planToWatch
    }

    public void removeFromList() {
        //Removes selected media from list.
    }
}
