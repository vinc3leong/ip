package skibidi;

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
