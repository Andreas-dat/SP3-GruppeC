import java.util.ArrayList;

public class Search {

    private static ArrayList<Media> filteredList = new ArrayList<>();


    public static ArrayList<Media> searchByRating(ArrayList<Media> media) {
        filteredList.clear();
        double choice = TextUI.promptDecimal("Enter a minimum rating from 1.0 to 10.0");

        for (Media m : media) {
            if (m.getRating() >= choice) {
                filteredList.add(m);
            }
        }
        return filteredList;
    }

    public static ArrayList<Media> searchByTitle(ArrayList<Media> media) {
        filteredList.clear();
        String search = TextUI.promptText("Search for a title");

        for (Media m : media) {
            if (m.getTitle().contains(search)) {
                filteredList.add(m);
            }
        }
        return filteredList;
    }
    public static ArrayList<Media> searchByYear(ArrayList<Media> media) {
        filteredList.clear();
        int choice = TextUI.promptNumeric("Type a calandar year");

        for (Media m : media) {
            if (m.getReleaseYear() == choice) {   //todo: change so that we search by decade?
                filteredList.add(m);
            }
        }
        return filteredList;
    }

    public static ArrayList<Media> searchByDecade(ArrayList<Media> media) {
        filteredList.clear();
        int choice = TextUI.promptNumeric("Type a decade:");
        if(choice % 10 != 0) {
            choice -= choice % 10;
        }

        for (Media m : media) {
            if (m.getReleaseYear() <= choice + 9 && m.getReleaseYear() >= choice) {   //todo: change so that we search by decade?
                filteredList.add(m);
            }
        }
        return filteredList;

    }

    public static ArrayList<Media> searchByCategory(ArrayList<Media> media) {
        filteredList.clear();
        for (Media m : media) {
            filteredList.add(m);
        }
        ArrayList<String> categories = new ArrayList<>();
        for (Categories c : Categories.values()) {
            categories.add(c.name());
        }

        int limit = TextUI.promptNumeric("How many categories do you want to add?");
        ArrayList<String> choices = TextUI.promptChoice(categories, limit, "Choose your categories");

        for (String choice : choices) {
            for (Media m : media) {
                String genres = m.getGenres().toString().toUpperCase();
                if (!genres.contains(choice)) {
                    filteredList.remove(m);
                }
            }
        }



        /* ArrayList<String> categories = new ArrayList<>();
        for (Categories c : Categories.values()) {
            categories.add(c.name());
        }

        int limit = TextUI.promptNumeric("How many categories do you want to add?");
        ArrayList<String> choices = TextUI.promptChoice(categories, limit, "Choose your categories");

        for (String choice : choices) {
            for (Media m : media) {
                if (m.toString().contains(choice)) {
                    filteredList.add(m);
                }
            }
        }

         */
        return filteredList;
    }
}
