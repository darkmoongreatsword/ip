package lewis;

/**
 * This command tells Lewis to mark or unmark a task from the tasklist
 * as complete.
 */
public class MarkUnmarkCommand extends Command {

    /** The index of the task to mark or unmark*/
    int taskIndex;
    /** The status that the task will change to */
    Task.Status updatedStatus = Task.Status.NOT_DONE;

    /** Public constructor for this command
     * @param input a string input propagated from the standard input stream
     */
    public MarkUnmarkCommand(String input) {
        if (input.startsWith("mark")) {
            this.updatedStatus = Task.Status.DONE;
            String[] tokens = input.split("mark");
            taskIndex = Integer.parseInt(tokens[1].trim());
        } else {
            String[] tokens = input.split("unmark");
            taskIndex = Integer.parseInt(tokens[1].trim());
        }
    }

    /**
     * Returns a description for the user on the usage of this command
     * @return a string description
     */
    public static String getHelpDescription() {
        return """
                Marks a task as complete or incomplete.
                Usage: mark <index>
                Usage: unmark <index>
                 The index of the task can be found by using the list command""";
    }


    /**
     * Marks or unmarks the task as complete.
     */
    @Override
    public void execute() {
        try {
            TaskList.update(taskIndex, updatedStatus);
            Storage.save();
        } catch (LewisException e) {
            Ui.printString(e.getMessage());
        }
    }
}
