package skibidi.command;

import skibidi.utils.Storage;
import skibidi.utils.TaskList;
import skibidi.utils.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public ListCommand() {
        super(COMMAND_WORD);
    }

    @Override
    public void execute(Storage storage, TaskList taskList) {
        Ui.printList(taskList);
    }
}
