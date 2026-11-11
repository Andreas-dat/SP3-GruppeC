import java.util.ArrayList;

public abstract class Media {
    private String title;
    private int releaseYear;
    private ArrayList<Categories> categories;
    private double rating;
    private int ID;
    private static int idCount;


    public Media(String title, ArrayList<Categories> categories, int releaseYear, double rating) {
        this.title = title;
        this.categories = categories;
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
        return getTitle() + " (" + getReleaseYear() + ") " + "Rating: " + getRating() + " " + getCategories();
    }

    public double getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Categories> getCategories() {
        return categories;
    }

    public int getReleaseYear() {
        return releaseYear;
    }
}
