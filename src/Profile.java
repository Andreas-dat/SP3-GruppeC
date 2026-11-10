import java.util.ArrayList;

public class Profile {

    private ArrayList<Media> planToWatch;
    private ArrayList<Media> hasWatched;
    private String nickname;
    private boolean isChild;

    public Profile(String nickname, boolean isChild) {
        this.nickname = nickname;
        this.isChild = isChild;
        this.planToWatch = new ArrayList<>();
        this.hasWatched = new ArrayList<>();
    }

    public Profile(String nickname, ArrayList<Media> planToWatch, ArrayList<Media> hasWatched, boolean isChild) {
        this.nickname = nickname;
        this.isChild = isChild;
        this.planToWatch = planToWatch;
        this.hasWatched = hasWatched;
    }

    public void addToList(Media media) {
        //This method adds to a list. Should work for both adding to hasWatched and planToWatch
    }

    public void removeFromList() {
        //Removes selected media from list.
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public ArrayList<Media> getHasWatched() {
        return hasWatched;
    }

    public ArrayList<Media> getPlanToWatch() {
        return planToWatch;
    }
}
