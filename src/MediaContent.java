import java.util.ArrayList;

public abstract class MediaContent {
    private final String title;
    private final int releaseYear;
    private ArrayList<Category> categories;
    private final double rating;
    private final int ID;
    private static int idCount = 0;

    public MediaContent(String title, double rating, int releaseYear) {
        this.title = title;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.ID = idCount;
        idCount++;
    }
}
