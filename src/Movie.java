import java.util.ArrayList;

public class Movie extends Media implements Watchable {

    Movie(String title, ArrayList<Categories> categories, int releaseYear, double rating) {
        super(title, categories, releaseYear, rating);
    }

    @Override
    public void play(Profile profile) {
        TextUI.displayMessage("Now playing " + this.toString());
        if (!profile.getHasWatched().contains(this)) {
            profile.addToHasWatched(this);
        }
    }
}
