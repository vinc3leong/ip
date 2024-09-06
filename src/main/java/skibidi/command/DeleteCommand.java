package skibidi.command;

import java.io.IOException;

import skibidi.exceptions.SkibidiException;
import skibidi.task.Task;
import skibidi.utils.Storage;
import skibidi.utils.TaskList;
import skibidi.utils.Ui;

/**
 * Represents a delete command.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    private final int index;

    /**
     * Creates a delete command.
     *
     * @param input Array of input strings
     * @throws SkibidiException If the input is invalid
     */
    public DeleteCommand(String[] input) throws SkibidiException {
        super(COMMAND_WORD);
        if (input.length < 2) {
            throw new SkibidiException("Error: delete index must not be empty. "
                    + "\nTo delete a task, use the format: delete <index>");
        } else {
            try {
                this.index = Integer.parseInt(input[1]) - 1;
            } catch (NumberFormatException e) {
                throw new SkibidiException("Error: delete index must be a number. "
                        + "\nTo delete a task, use the format: delete <index>");
            }
        }
    }

    @Override
    public void execute(Storage storage, TaskList taskList) throws IOException {
        Task task = taskList.deleteTask(index);
        Ui.printDelete(task, taskList);
        storage.save(taskList);
    }
}
