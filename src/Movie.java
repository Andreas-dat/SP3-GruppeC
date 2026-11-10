import java.util.ArrayList;

public class Movie extends Media implements Watchable {

    Movie(String title, int releaseYear, ArrayList<String> genres, double rating) {
        super(title, releaseYear, genres, rating);
    }
    Movie(String title,double rating,int releaseYear) {
        super(title, rating, releaseYear);
    }


    @Override
    public void play() {
        TextUI.displayMessage("Now playing " + this.toString());
    }
}
