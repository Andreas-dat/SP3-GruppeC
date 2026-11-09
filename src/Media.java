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

    public Media(String title, double rating, int releaseYear) {
       this.title = title;
       this.releaseYear = releaseYear;
       this.rating = rating;
    }

    public int getID() {
        return ID;
    }
}
