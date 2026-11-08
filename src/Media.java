import java.util.ArrayList;

public abstract class Media {
    private String title;
    private int releaseYear;
    private ArrayList<String> genres;
    private double rating;
    private int ID;
    private static int idCount;

    Media(String title, int releaseYear, ArrayList<String> genres, double rating) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.genres = genres;
        this.rating = rating;
    }
}
