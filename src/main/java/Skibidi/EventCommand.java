package Skibidi;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";
    private final String description;
    private final String from;
    private final String to;
    private final MilitaryTime startTime;
    private final MilitaryTime endTime;

    public EventCommand(String[] input) throws SkibidiException {
        super(COMMAND_WORD);
        if (input.length < 8) {
            throw new SkibidiException("Error: event description must not be empty. " +
                    "\nTo add an event task, use the format: event <description> /from <date> <time> /to <date> <time>");
        }

        if (Arrays.stream(input).noneMatch(s -> s.equals("/from")) ||
                Arrays.stream(input).noneMatch(s -> s.equals("/to"))) {
            throw new SkibidiException("Error: event description must not be empty. " +
                    "\nTo add an event task, use the format: event <description> /from <date> <time> /to <date> <time>");
        }

        int startFromIndex = Arrays.asList(input).indexOf("/from") + 1;
        int endsAtIndex = Arrays.asList(input).indexOf("/to") + 1;
        LocalDate startFrom = LocalDate.parse(input[startFromIndex]);
        String formattedStartFrom = startFrom.format(DateTimeFormatter.ofPattern("MM dd yyyy"));
        LocalDate endsAt = LocalDate.parse(input[endsAtIndex]);
        String formattedEndsAt = endsAt.format(DateTimeFormatter.ofPattern("MM dd yyyy"));

        this.description = input[1];
        this.from = formattedStartFrom;
        this.to = formattedEndsAt;
        this.startTime = new MilitaryTime(input[startFromIndex + 1]);
        this.endTime = new MilitaryTime(input[endsAtIndex + 1]);

    }

    @Override
    public void execute(Storage storage, TaskList taskList) throws IOException {
        Task task = new Event(description, from, startTime, to, endTime);
        taskList.addTask(task);
        Ui.printAdd(task, taskList);
        storage.save(taskList);
    }
}
