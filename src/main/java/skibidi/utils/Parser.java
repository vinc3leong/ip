package skibidi.utils;


import skibidi.command.Command;
import skibidi.command.DeadlineCommand;
import skibidi.command.DeleteCommand;
import skibidi.command.EventCommand;
import skibidi.command.ExitCommand;
import skibidi.command.FindCommand;
import skibidi.command.InvalidCommand;
import skibidi.command.ListCommand;
import skibidi.command.MarkCommand;
import skibidi.command.TodoCommand;
import skibidi.command.UnmarkCommand;
import skibidi.exceptions.SkibidiException;
import skibidi.task.Deadline;
import skibidi.task.Event;
import skibidi.task.Task;
import skibidi.task.Todo;

/**
 * Represents a parser.
 */
public class Parser {

    /**
     * Parses the input and returns the corresponding command.
     *
     * @param input The input to be parsed.
     * @return The corresponding command.
     * @throws SkibidiException If the input is invalid.
     */
    public static Command parse(String input) throws SkibidiException {
        String[] words = input.split(" ");
        String command = words[0];
        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "delete":
            return new DeleteCommand(words);
        case "todo":
            return new TodoCommand(words);
        case "deadline":
            return new DeadlineCommand(words);
        case "event":
            return new EventCommand(words);
        case "mark":
            return new MarkCommand(words);
        case "unmark":
            return new UnmarkCommand(words);
        case "find":
            return new FindCommand(words);
        default:
            return new InvalidCommand();
        }
    }

    /**
     * Parses the task and returns the corresponding task.
     *
     * @param task The task to be parsed.
     * @return The corresponding task.
     * @throws SkibidiException If the task is invalid.
     */
    public static Task parseTask(String task) throws SkibidiException {
        String[] words = task.split(" \\| ");
        String taskType = words[0];
        switch (taskType) {
        case "T":
            boolean isDone = words[1].equals("1");
            String description = words[2];
            Todo todo = new Todo(description);
            if (isDone) {
                todo.markAsDone();
                return todo;
            } else {
                return todo;
            }
        case "D":
            isDone = words[1].equals("1");
            description = words[2];
            String by = words[3];
            MilitaryTime time = new MilitaryTime(TimeUtil.convertStandardToMilitary(words[4]));
            Deadline deadline = new Deadline(description, by, time);
            if (isDone) {
                deadline.markAsDone();
                return deadline;
            } else {
                return deadline;
            }
        case "E":
            isDone = words[1].equals("1");
            description = words[2];
            String from = words[3];
            MilitaryTime startTime = new MilitaryTime(TimeUtil.convertStandardToMilitary(words[4]));
            String to = words[5];
            MilitaryTime endTime = new MilitaryTime(TimeUtil.convertStandardToMilitary(words[4]));
            Event event = new Event(description, from, startTime, to, endTime);
            if (isDone) {
                event.markAsDone();
                return event;
            } else {
                return event;
            }
        default:
            throw new SkibidiException("Error: Invalid task type detected in file.");
        }
    }
}
