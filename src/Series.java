import java.util.ArrayList;

public class Series extends Media implements Watchable{

    private int endOfRelease;
    ArrayList<Integer> seasonsAndEpisodes;

    public Series(String title, ArrayList<Categories> genre, double rating, int releaseYear, int endOfRelease, ArrayList<Integer> seasonsAndEpisodes) {
        super(title, genre, releaseYear, rating);
        this.endOfRelease = endOfRelease;
        this.seasonsAndEpisodes = seasonsAndEpisodes;

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
