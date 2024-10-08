package lewis;

/**
 * This class implements a command that can be interpreted by Lewis to execute
 * This class supports the following methods:
 * (i) execute()
 * (ii) isExit()
 */
public abstract class Command {
    /**
     * Executes the command. Subclasses of this class must implement this logic.
     */
    public abstract void execute();


    /**
     * Returns the exit flag for Lewis. False by default.
     * Only an exit command needs to override this method
     */
    public boolean isExit() {
        return false;
    }
}
