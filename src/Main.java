import java.util.ArrayList;
import java.util.Arrays;

public class Main {


    public static void main(String[] args) {

        ArrayList<String> data = FileIO.readData("data/movies");

        //Læs movie
        for (String s : data) {
            String[] values = s.split(";");             //splitter med ;
            String title = values[0];                         //tildeler værdier
            int releaseYear = Integer.parseInt(values[1]);    //parse til int
            ArrayList<String> genres = new ArrayList<String>(Arrays.asList(values[2].split(",")));   //Opretter ArrayList for genrer (vi splitter Values[2] med "," og omdanner til ArrayList)
            double rating = Double.parseDouble(values[3]);

            Movie movie = new Movie(title, releaseYear, genres, rating);        //Movie initialiseres
        }


        for (String s : data) {
            String[] values = s.split(";");             //splitter med ;
            String title = values[0];                         //tildeler værdier
            int releaseYear = Integer.parseInt(values[1]);    //parse til int
            String[] genresArray = values[2].split(",");
            ArrayList<String> genres = new ArrayList<String>(Arrays.asList(genresArray));
            double rating = Double.parseDouble(values[3]);

            Movie movie = new Movie(title, releaseYear, genres, rating);
        }

    }
}
