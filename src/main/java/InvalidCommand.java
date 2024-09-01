public class InvalidCommand extends Command {
    public static final String COMMAND_WORD = "invalid";

    public InvalidCommand() {
        super(COMMAND_WORD);
    }

    @Override
    public void execute(Storage storage, TaskList taskList) {
        Ui.printInvalidCommand();
    }
}
