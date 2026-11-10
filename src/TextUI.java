import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TextUI {

    /*
    ' shows the options, promts for choices until limit is reached, and returns the user's choices as a list of Strings
    + ArrayList<String> promptChoice(ArrayList<String> options, int limit, String msg)
    */

    static Scanner scan = new Scanner(System.in);

    public static String promptText(String msg) {
        System.out.println(msg);
        String input = scan.nextLine();
        return input;
    }

    public static void displayMessage(String message) {
        System.out.println(message);
    }

    public static boolean promptBinary(String msg) {
        String input = promptText(msg).toLowerCase();
        if (input.equals("y")) {
            return true;
        }

        if (input.equals("n")) {
            return false;
        }
        return promptBinary(msg);
    }

    public static int promptNumeric(String msg) {
        System.out.println(msg);
        int input = 0;
        try {
            input = Integer.parseInt(scan.nextLine());
        } catch (InputMismatchException e) {
            System.out.println("Skriv et tal");
            input = promptNumeric(msg);
        }

        return input;
    }

    public static void displayList(ArrayList<String> list, String msg, boolean numbered) {//parameter der forstæller OM listen er nummereret eller ej.
        System.out.println(msg);

        for (int i = 0; i < list.size(); i++) {

            // String s = numbered?i+1+". "+ list.get(i):list.get(i);
            String s;
            if (numbered) {
                s = i + 1 + ". " + list.get(i);

            } else {

                s = list.get(i);
            }
            System.out.println(s);
        }
    }

    public ArrayList<String> promptChoice(ArrayList<String> options, int limit, String msg) {
        ArrayList<String> choices = new ArrayList<>();


        //kør et while loop, så længe limit ikke er nået
        while (choices.size() < limit) {
            int input = promptNumeric(msg);
            if (input > 0 && input <= options.size()) {
                String choice = options.get(input - 1);
                choices.add(choice);
            } else {
                System.out.println("Invalid input");
            }
        }
        return choices;
    }


    public static int promptMenuChoice(ArrayList<String> menu, String msg) {
        displayList(menu, msg, true);
        int input = promptNumeric("Type a number:");
        if(input > menu.size()) {
            System.out.println("Wrong number should be between 1 and " + menu.size());
            promptMenuChoice(menu, msg);
        }
        return input;
    }
}