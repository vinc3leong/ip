package skibidi.command;

import skibidi.utils.Storage;
import skibidi.utils.TaskList;
import skibidi.utils.Ui;

/**
 * Represents an exit command.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";
    public ExitCommand() {
        super(COMMAND_WORD);
    }

    @Override
    public void execute(Storage storage, TaskList taskList) {
        Ui.printGoodbyeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
