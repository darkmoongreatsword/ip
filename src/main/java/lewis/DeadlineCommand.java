package lewis;

import java.time.format.DateTimeParseException;

/**
 * This command creates a new deadline command, and handles the appropriate
 * parsing of user input, UI and entering the deadline into the tasklist.
 */
public class DeadlineCommand extends Command {
    /** Each command has one unique deadline associated to it */
    private final Deadline deadline;

    /**
     * Private constructor for this class
     * @param deadline A deadline
     */
    private DeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }


    /**
     * Factory method for creating a DeadlineCommand. This method should be used
     * when parsing a new DeadlineCommand from the command line
     * @param input A string read from the (standard) input stream
     * @return A new DeadlineCommand
     * @throws LewisException if the input is in an incorrect format
     */
    public static DeadlineCommand of(String input) throws LewisException {
        /* Important, Lewis shouldn't try to parse a null(not empty) string */
        assert input != null : "Input cannot be null";
        try {
            //Get the rest of the command input
            String[] tokens = input.split("deadline");

            //Separate the deadline info into description, date and time
            String[] deadlineInfo = tokens[1].split("/by");
            String deadlineDescription = deadlineInfo[0].trim();
            String date;
            String time = "23:59"; //Default deadline time
            if (deadlineInfo[1].trim().length() > 10) { //Check if user only entered the date
                String[] dateAndTime = deadlineInfo[1].trim().split(" ");
                date = dateAndTime[0].trim();
                time = dateAndTime[1].trim();
            } else {
                date = deadlineInfo[1].trim();
            }

            Deadline newDeadline = Deadline.of(deadlineDescription, date, time);
            return new DeadlineCommand(newDeadline);
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new LewisException("Hey, I don't understand your command! Type your "
                    + "command in this format:\n"
                    + "deadline <description> /by YYYY-MM-DD HH:MM");
        }
    }

    /**
     * Returns a description of usage of the deadline command for the user
     * @return A help description
     */
    public static String getHelpDescription() {
        return """
                Enters a new deadline into the tasklist.
                 Usage: deadline <description> /by <date> <time> where\s
                <date> is in format YYYY-MM-DD and\s
                <time> is in format HH:MM
                If time is not specified, the default value is 23:59""";
    }

    /**
     * Executes the deadline command
     * Parses the user input as a deadline and enters it into the tasklist
     */
    @Override
    public void execute() {
        TaskList.add(deadline);
        Ui.printTask(deadline);
        Storage.save();
    }
}
