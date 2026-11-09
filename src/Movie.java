import java.util.ArrayList;

public class Movie extends Media {

    Movie(String title, int releaseYear, ArrayList<String> genres, double rating) {
        super(title, releaseYear, genres, rating);
    }
    Movie(String title,double rating,int releaseYear) {
        super(title, rating, releaseYear);
    }
}
