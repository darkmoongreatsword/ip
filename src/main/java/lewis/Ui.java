package lewis;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * Implements a user interface. This handles user input and passes it to
 * the parser. It also handles output to the user, via the terminal
 */
public class Ui {
    private Ui() {
    }

    /** Scanner object for reading user input*/
    private static final Scanner SCANNER = new Scanner(System.in);

    public static final String LINE = "------------------------------------------------------------------------------------------";
    public static void printLine() {
        System.out.println(LINE);
    }

    /**
     * Prints a string representation of a given task to the terminal
     * This usage should be used when creating a new task
     * @param task the task to be printed
     */
    public static void printTask(Task task) {
        System.out.println("I've got it, I've added this task to your tasklist.");
        System.out.println(task.toString());
        printLine();
    }

    /**
     * Prints a string representation of a given task to be deleted.
     * This usage should be used when deleting a task
     * @param task the task to be deleted
     */
    public static void printDeletedTask(Task task) {
        System.out.println("I've obliterated this task from the list. You're welcome");
        System.out.println(task.toString());
    }

    /**
     * Prints a given string to the terminal
     * @param string the string to be printed
     */
    public static void printString(String string) {
        System.out.println(string);
    }

    /**
     * Reads the next line of user input and returns it as a string
     * @return a string
     */
    public static String readLine() {
        return SCANNER.nextLine();
    }

    /**
     * Prints the list of tasks as strings separated by a new line
     * @param listToPrint an array of strings representing tasks.
     */
    public static void printList(ArrayList<String> listToPrint) {
        for (String string : listToPrint) {
            System.out.println(string);
        }
    }



}
