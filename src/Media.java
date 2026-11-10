import java.util.ArrayList;

public abstract class Media {
    private String title;
    private int releaseYear;
    private ArrayList<Categories> genres;
    private double rating;
    private int ID;
    private static int idCount;


    public Media(String title, ArrayList<Categories> genre, int releaseYear, double rating) {
       this.title = title;
       this.genres = genre;
       this.releaseYear = releaseYear;
       this.rating = rating;
       this.ID = idCount;
       idCount++;
    }

    public int getID() {
        return ID;
    }

    @Override
    public String toString() {
        return title + " " + releaseYear + " " + ID + genres;
    }
}
