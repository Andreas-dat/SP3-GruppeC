import java.util.ArrayList;

public class Series extends Media implements Watchable{

    private int endOfRelease;
    ArrayList<Integer> seasonsAndEpisodes;  //Nested ArrayLists for info om seasons og episodes

    public Series(String title, double rating, int releaseYear, int endOfRelease, ArrayList<Integer> seasonsAndEpisodes) {
        super(title, rating, releaseYear);
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
}
