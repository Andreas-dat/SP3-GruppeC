import java.util.ArrayList;

public class Series extends Media implements Watchable{

    private int endOfRelease;
    ArrayList<Integer> episodesInSeason;

    public Series(String title, ArrayList<Categories> categories, double rating, int releaseYear, int endOfRelease, ArrayList<Integer> episodesInSeason) {
        super(title, categories, releaseYear, rating);
        this.endOfRelease = endOfRelease;
        this.episodesInSeason = episodesInSeason;

    }

    @Override
    public void play(Profile profile) {
        TextUI.displayMessage("Now playing " + this.toString());
        if (!profile.getHasWatched().contains(this)) {
            profile.addToHasWatched(this);
        }
    }

    public int getEndOfRelease() {
        return endOfRelease;
    }

    @Override
    public String toString() {
        if(getEndOfRelease() == -1) {
            return getTitle() + " (" + getReleaseYear() + "- ) " + "Rating: " + getRating() + " " + getCategories();
        }
        return getTitle() + " (" + getReleaseYear() + "-" + getEndOfRelease() + ") " + "Rating: " + getRating() + " " + getCategories();
    }

}
