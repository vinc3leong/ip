package Skibidi;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";

    public int index;

    public UnmarkCommand(String[] input) throws SkibidiException {
        super(COMMAND_WORD);
        if (input.length == 1) {
            throw new SkibidiException("Error: unmark index must not be empty. " +
                    "\nTo unmark a task as not done, use the format: unmark <index>");
        } else {
            this.index = Integer.parseInt(input[1]) - 1;
        }
    }

    @Override
    public void execute(Storage storage, TaskList taskList) {
        if (index < 0 || index > taskList.size()) {
            Ui.printInvalidIndex();
        }
        taskList.unmarkTaskAsDone(index );
        Ui.printMarkAsUndone(taskList.getTask(index ));
    }
}
