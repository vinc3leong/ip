package skibidi.command;

import skibidi.exceptions.SkibidiException;
import skibidi.utils.Storage;
import skibidi.utils.TaskList;
import skibidi.utils.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    private int index;

    /**
     * Creates a mark command.
     *
     * @param input Array of input strings
     * @throws SkibidiException If the input is invalid
     */
    public MarkCommand(String[] input) throws SkibidiException {
        super(COMMAND_WORD);
        if (input.length == 1) {
            throw new SkibidiException("Error: mark index must not be empty. "
                    + "\nTo mark a task as done, use the format: mark <index>");
        } else {
            this.index = Integer.parseInt(input[1]) - 1;
        }
    }

    @Override
    public void execute(Storage storage, TaskList taskList) {
        if (index < 0 || index > taskList.size()) {
            Ui.printInvalidIndex();
        }
        taskList.markTaskAsDone(index );
        Ui.printMarkAsDone(taskList.getTask(index ));
    }
}
