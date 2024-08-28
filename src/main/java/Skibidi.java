import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Skibidi {

    private static List<Task> tasks = new ArrayList<>();

    public static void addTask(Task task) throws IOException {
            tasks.add(task);
            writeToFile("src/main/data/skibidi.txt");
            System.out.println("Got it. I've added this task:");
            System.out.println(task.toString());
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void writeToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        for (int i = 0; i < tasks.size(); i++) {
            fw.write( i + 1 + tasks.get(i).toString() + "\n");
        }
        fw.close();
    }

    public static void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i+1 +"." + tasks.get(i).toString());
            }
        }
    }

    public static void markTaskAsDone(String[] words) throws SkibidiException, IOException {
        if (words.length != 2) {
            throw new SkibidiException("Error: mark command must be followed by a task number. " +
                    "\nTo mark a task as done, use the format: mark <task number>");
        }
        int taskNumber = Integer.parseInt(words[1]);
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            tasks.get(taskNumber - 1).markAsDone();
            writeToFile("src/main/data/skibidi.txt");
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(taskNumber - 1).toString());
        } else {
            throw new SkibidiException("Error: Task number " + taskNumber + " is out of bounds. " +
                    "You currently have " + tasks.size() + " tasks.");
        }
    }

    public static void unmarkTask(String[] words) throws SkibidiException, IOException {
        if (words.length != 2) {
            System.out.println("Error: Unmark command must be followed by a task number. " +
                    "\nTo unmark a task as not done, use the format: unmark <task number>");
            return;
        }
        int taskNumber = Integer.parseInt(words[1]);
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            tasks.get(taskNumber - 1).markAsNotDone();
            writeToFile("src/main/data/skibidi.txt");
            System.out.println("Ok, I've marked this task as not done yet:");
            System.out.println(tasks.get(taskNumber - 1).toString());
        } else {
            throw new SkibidiException("Error: Task number " + taskNumber + " is out of bounds.");
        }

    }

    public static void deleteTask(String[] words) throws SkibidiException, IOException {
        if (words.length != 2) {
            System.out.println("Error: Delete command must be followed by a task number. " +
                    "\nTo delete a task, use the format: delete <task number>");
            return;
        }

        int taskNumber = Integer.parseInt(words[1]);
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            Task task = tasks.remove(taskNumber - 1);
            writeToFile("src/main/data/skibidi.txt");
            System.out.println("Ok, I've removed this task:");
            System.out.println(task.toString());
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } else {
            throw new SkibidiException("Error: Task number " + taskNumber + " is out of bounds.");
        }

    }

    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }
    public static void main(String[] args) {
        String name = "Skibidi";
        System.out.println("Ohio skibidi on the wall, who's the skibidiest of 'em all?!!" +
                "\nHello! I'm " + name + " the skibidiest of 'em all");

        try {
            printFileContents("src/main/data/skibidi.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());

        }
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String input = scanner.nextLine();
                if (Objects.equals(input.trim(), "bye")) {
                    System.out.println("Bye! Hope to never see you again soon!");
                    break;
                } else if (Objects.equals(input.trim(), "list")) {
                    listTasks();
                } else if (input.startsWith("mark")) {
                    String[] words = input.split(" ");
                    markTaskAsDone(words);
                } else if (input.startsWith("unmark")) {
                    String[] words = input.split(" ");
                    unmarkTask(words);
                } else if (input.startsWith("todo")) {
                    if (input.length() < 6) {
                        throw new SkibidiException("Error: todo description must not be empty. " +
                                "\nTo add a todo task, use the format: todo <description>");
                    }
                    Task task = new Todo(input.substring(5).trim());
                    addTask(task);

                } else if (input.startsWith("deadline")) {
                    if (!input.contains(" /by ")) {
                        throw new SkibidiException("Error: Deadline description must include a deadline. " +
                                "\nTo add a deadline task, use the format: deadline <description> /by <date> <time>" +
                                " where <date> is in the format yyyy-mm-dd and <time> is in the format HHmm");
                    }
                    String[] words = input.split(" /by ");
                    String[] dateTime = words[1].split(" ");
                    if (words.length != 2 || dateTime.length != 2) {
                        throw new SkibidiException("To add a deadline task, use the format: " +
                                "deadline <description> /by <date> <time> where <date> is in the format yyyy-mm-dd " +
                                "and <time> is in the format HHmm");
                    }
                    try {
                        LocalDate deadline = LocalDate.parse(dateTime[0]);
                        String formattedDeadline = deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        MilitaryTime time = new MilitaryTime(dateTime[1]);

                        Task task = new Deadline(words[0].substring(9).trim(), formattedDeadline, time);
                        addTask(task);

                    } catch (DateTimeParseException e) {
                        throw new SkibidiException("Error: Deadline must be a valid date in the format yyyy-mm-dd");
                    }
                } else if (input.startsWith("event")) {
                    if (!input.contains(" /from ") || !input.contains(" /to ")) {
                        throw new SkibidiException("Error: Event description must include both start and end times. " +
                                "\nTo add an event task, use the format: " +
                                "event <description> /from <start date> <start time> /to <end date> <end time> " +
                                "\nwhere <start date> and <end date> is in the format yyyy-mm-dd and " +
                                "<start time> and <end time> is in the format HHmm " );
                    }

                    String[] words = input.split(" /from | /to ");
                    String[] fromDateTime = words[1].split(" ");
                    String[] toDateTime = words[2].split(" ");
                    if (words.length != 3 || fromDateTime.length != 2 || toDateTime.length != 2) {
                        throw new SkibidiException("To add an event task, use the format: " +
                                "event <description> /from <start date> <start time> /to <end date> <end time> " +
                                "where <start date> and <end date> is in the format yyyy-mm-dd and " +
                                "<start time> and <end time> is in the format HHmm " );
                    }
                    try {
                        LocalDate from = LocalDate.parse(fromDateTime[0]);
                        LocalDate to = LocalDate.parse(toDateTime[0]);
                        String formattedFrom = from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        String formattedTo = to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                        MilitaryTime startTime = new MilitaryTime(fromDateTime[1]);
                        MilitaryTime endTime = new MilitaryTime(toDateTime[1]);

                        Task task = new Event(words[0].substring(6).trim(), formattedFrom, startTime,
                                formattedTo, endTime);
                        addTask(task);

                    } catch (DateTimeParseException e) {
                        throw new SkibidiException("Error: Event start and end times must be in the format yyyy-mm-dd");
                    }
                } else if (input.startsWith("delete")) {
                    String[] words = input.split(" ");
                    deleteTask(words);
                } else {
                    System.out.println("I'm sorry, but I don't know what that means :-(");
                }
            } catch (SkibidiException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Error: Task number must be an integer.");
            }

        }
        scanner.close();
    }
}
