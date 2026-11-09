import java.util.ArrayList;

public class Series extends MediaContent implements Watchable{

    private int endOfRelease;
    ArrayList<ArrayList<Integer>> seasonsAndEpisodes = new ArrayList<ArrayList<Integer>>();  //Nested ArrayLists for info om seasons og episodes

    public Series(String title, double rating, int releaseYear, int endOfRelease, ArrayList<ArrayList<Integer>> seasonsAndEpisodes) {
        super(title, rating, releaseYear);
        this.endOfRelease = endOfRelease;

    }

    @Override
    public void play() {
        //Plays the specific episode from the series. Prints message "Now playing..." and saves it to profile's hasWatched
    }
}
