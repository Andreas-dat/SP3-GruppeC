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

    public void addToHasWatched(Media media) {
        hasWatched.add(media);
    }

    public void addToPlanToWatch(Media media) {
        planToWatch.add(media);
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

    public void toggleWatchList(Media media) {   //Adds media to watchlist if not already there. Otherwise, removes it
        if (planToWatch.contains(media)) {
            planToWatch.remove(media);
        } else {
            planToWatch.add(media);
        }
    }

    public void removeFromHasWatched(Media media) {
        hasWatched.remove(media);
    }

    private String listToString(ArrayList<Media> media) {
        StringBuilder stringBuilder = new StringBuilder();
        if(media.isEmpty()) {
            stringBuilder.append("; null");
        }
        for (int i = 0; i < media.size(); i++) {
            stringBuilder.append(media.get(i).getID());
            if (i == media.size() - 1) {
                stringBuilder.append("; ");
                break;
            }
            stringBuilder.append(", ");
        }
        return stringBuilder.toString();
    }

    public String toCSV() {
        StringBuilder stringBuilder = new StringBuilder(nickname + "; ");
        stringBuilder.append(isChild);
        stringBuilder.append(listToString(planToWatch));
        stringBuilder.append(listToString(hasWatched));

        return stringBuilder.toString();
    }

    public Boolean getIsChild() {
        return isChild;
    }
}
