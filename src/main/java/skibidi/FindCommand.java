package skibidi;

/**
 * Represents a command to find tasks in the task list.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    public String keyword;

    public FindCommand(String[] input) throws SkibidiException {
        super(COMMAND_WORD);
        if (input.length == 1) {
            throw new SkibidiException("Error: find keyword must not be empty. " +
                    "\nTo find a task, use the format: find <keyword>");
        } else {
            this.keyword = everythingAfterCommand(input);
        }
    }

    private String everythingAfterCommand(String[] input) {
        StringBuilder description = new StringBuilder();
        for (int i = 1; i < input.length; i++) {
            description.append(input[i]).append(" ");
        }
        return description.toString().trim();
    }

    @Override
    public void execute(Storage storage, TaskList taskList) {
        TaskList matchingTasks = taskList.findTask(keyword);
        Ui.printFoundTasks(matchingTasks);
    }

}
