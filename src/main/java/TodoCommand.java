import java.io.IOException;

public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    private final String description;
    public TodoCommand(String[] input) throws SkibidiException {
        super(COMMAND_WORD);
        if (input.length < 2) {
            throw new SkibidiException("Error: todo description must not be empty. " +
                    "\nTo add a todo task, use the format: todo <description>");
        } else {
            this.description = everythingAfterCommand(input);
        }
    }

    public String everythingAfterCommand(String[] input) {
        StringBuilder description = new StringBuilder();
        for (int i = 1; i < input.length; i++) {
            description.append(input[i]).append(" ");
        }
        return description.toString().trim();
    }
    @Override
    public void execute(Storage storage, TaskList taskList) throws IOException {
        Task task = new Todo(description);
        taskList.addTask(task);
        Ui.printAdd(task, taskList);
        storage.save(taskList);
    }
}
