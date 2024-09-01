import java.io.IOException;

public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    private final int index;

    public DeleteCommand(String[] input) throws SkibidiException {
        super(COMMAND_WORD);
        if (input.length < 2) {
            throw new SkibidiException("Error: delete index must not be empty. " +
                    "\nTo delete a task, use the format: delete <index>");
        } else {
            try {
                this.index = Integer.parseInt(input[1]) - 1;
            } catch (NumberFormatException e) {
                throw new SkibidiException("Error: delete index must be a number. " +
                        "\nTo delete a task, use the format: delete <index>");
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
