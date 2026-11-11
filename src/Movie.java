import java.util.ArrayList;

public class Movie extends Media implements Watchable {

    Movie(String title, ArrayList<Categories> genres, int releaseYear, double rating) {
        super(title, genres, releaseYear, rating);
    }

    @Override
    public void play(Profile profile) {
        TextUI.displayMessage("Now playing " + this.toString());
        if (!profile.getHasWatched().contains(this)) {
            profile.addToHasWatched(this);
        }
    }
}
