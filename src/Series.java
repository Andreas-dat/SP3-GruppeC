import java.util.ArrayList;

public class Series extends Media implements Watchable{

    private int endOfRelease;
    ArrayList<Integer> seasonsAndEpisodes;  //Nested ArrayLists for info om seasons og episodes

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
        //Plays the specific episode from the series. Prints message "Now playing..." and saves it to profile's hasWatched
    }

    public int getEndOfRelease() {
        return endOfRelease;
    }

    @Override
    public String toString() {
        if(getEndOfRelease() == -1) {
            return getTitle() + " (" + getReleaseYear() + "- ) " + "Rating: " + getRating() + " " + getGenres() + ". ID: " + getID();
        }
        return getTitle() + " (" + getReleaseYear() + "-" + getEndOfRelease() + ") " + "Rating: " + getRating() + " " + getGenres() + ". ID: " + getID();
    }

}
