public class Movie extends Media implements Watchable{

    public Movie(String title, double rating, int releaseYear) {
        super(title, rating, releaseYear);
    }

    @Override
    public void play() {

    }
}
