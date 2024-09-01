package Skibidi;

public class Parser {
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
            default:
                return new InvalidCommand();
        }
    }



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
