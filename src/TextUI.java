import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TextUI {

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
            System.out.println("Write a number");
            input = promptNumeric(msg);
        } catch (NumberFormatException e) {
            System.out.println("Input cant be empty");
            input = promptNumeric(msg);
        }
        return input;
    }

    public static double promptDecimal(String msg) {
        System.out.println(msg);
        double input = 0.0;
        try {
            input = Double.parseDouble(scan.nextLine());
        } catch (InputMismatchException e) {
            System.out.println("Write a decimal");
            input = promptDecimal(msg);
        } catch (NumberFormatException e) {
            System.out.println("Input can't be empty");
            input = promptNumeric(msg);
        }
        return input;
    }

    public static void displayMenu(ArrayList<String> list, String msg, boolean numbered) {//parameter der forstæller OM listen er nummereret eller ej.
        System.out.println(msg);

        for (int i = 0; i < list.size(); i++) {

            String s;
            if (numbered) {
                if (i == list.size() -1) {
                    s = 0 + ". " + list.get(i);
                } else {
                    s = i + 1 + ". " + list.get(i);
                }
            } else {
                s = list.get(i);
            }
            System.out.println(s);
        }
    }

    public static void displayList(ArrayList<String> list, String msg, boolean numbered) {//parameter der forstæller OM listen er nummereret eller ej.
        System.out.println(msg);

        for (int i = 0; i < list.size(); i++) {

            String s;
            if (numbered) {
                s = i + 1 + ". " + list.get(i);
            } else {
                s = list.get(i);
            }
            System.out.println(s);
        }
    }

    public static ArrayList<String> promptChoice(ArrayList<String> options, int limit, String msg) {
        displayList(options, msg, true);
        ArrayList<String> choices = new ArrayList<>();

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
        displayMenu(menu, msg, true);
        int input = promptNumeric("Type a number:");
        if(input > menu.size() || input < 0) {
            System.out.println("Wrong number should be between 1 and " + menu.size());
            promptMenuChoice(menu, msg);
        }
        return input;
    }
}