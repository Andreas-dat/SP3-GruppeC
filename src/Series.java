import java.util.ArrayList;

public class Series extends Media{

    private int endOfRelease;
    private ArrayList<Season> seasons;

    Series(String title, int releaseYear, ArrayList<String> genres, double rating) {
        super(title, releaseYear, genres, rating);
    }
}
