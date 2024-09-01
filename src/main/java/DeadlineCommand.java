import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private final String description;
    private final String by;
    private final MilitaryTime time;

    public DeadlineCommand(String[] input) throws SkibidiException, DateTimeParseException {
        super(COMMAND_WORD);
        if (input.length < 5) { // Check if the input is empty
            throw new SkibidiException("Error: deadline description must not be empty. " +
                    "\nTo add a deadline task, use the format: deadline <description> /by <date> <time>" +
                    " where <date> is in the format yyyy-mm-dd and <time> is in the format HHmm.");
        }

        if (Arrays.stream(input).noneMatch(s -> s.equals("/by"))) {
            System.out.println(input[2]);
            throw new SkibidiException("Error: To add a deadline task, use the format: " +
                    "deadline <description> /by <date> <time>" +
                    " where <date> is in the format yyyy-mm-dd and <time> is in the format HHmm.");
        }

        int dateIndex = Arrays.asList(input).indexOf("/by") + 1;
        int timeIndex = Arrays.asList(input).indexOf("/by") + 2;
        LocalDate deadline = LocalDate.parse(input[dateIndex]);
        String formattedDeadline = deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        MilitaryTime time = new MilitaryTime(input[timeIndex]);

        this.description = input[1];
        this.by = formattedDeadline;
        this.time = time;

    }

    @Override
    public void execute(Storage storage, TaskList taskList) throws IOException {
        Task task = new Deadline(description, by, time);
        taskList.addTask(task);
        Ui.printAdd(task, taskList);
        storage.save(taskList);
    }
}
