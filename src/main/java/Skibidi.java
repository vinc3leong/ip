import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Skibidi {

    private static List<Task> tasks = new ArrayList<>();

    public static void addTask(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
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

    public static void markTaskAsDone(String[] words) throws SkibidiException {
        try {
            if (words.length != 2) {
                throw new SkibidiException("Error: mark command must be followed by a task number. \nTo mark a task as done, use the format: mark <task number>");
            }
            int taskNumber = Integer.parseInt(words[1]);
            if (taskNumber > 0 && taskNumber <= tasks.size()) {
                tasks.get(taskNumber - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks.get(taskNumber - 1).toString());
            } else {
                throw new SkibidiException("Error: Task number " + taskNumber + " is out of bounds. You currently have " + tasks.size() + " tasks.");
            }
        } catch (NumberFormatException e) {
            throw new SkibidiException("Error: Task number must be an integer.");
        } catch (SkibidiException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void unmarkTask(String[] words) {
        if (words.length != 2) {
            System.out.println("Error: Unmark command must be followed by a task number. \nTo unmark a task as not done, use the format: unmark <task number>");
            return;
        }
        try {
            int taskNumber = Integer.parseInt(words[1]);
            if (taskNumber > 0 && taskNumber <= tasks.size()) {
                tasks.get(taskNumber - 1).markAsNotDone();
                System.out.println("Ok, I've marked this task as not done yet:");
                System.out.println(tasks.get(taskNumber - 1).toString());
            } else {
                System.out.println("Error: Task number " + taskNumber + " is out of bounds.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Task number must be an integer.");
        }
    }

    public static void main(String[] args) throws SkibidiException {
        String name = "Skibidi";

        System.out.println("Ohio skibidi on the wall, who's the skibidiest of 'em all?!!" +
                "\nHello! I'm " + name + " the skibidiest of 'em all" + "\nWhat can I do for you?");

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
                        throw new SkibidiException("Error: todo description must not be empty. \nTo add a todo task, use the format: todo <description>");
                    }
                    Task task = new Todo(input.substring(5).trim());
                    addTask(task);

                } else if (input.startsWith("deadline")) {
                    if (input.length() < 9) {
                        throw new SkibidiException("Error: Deadline description must include a deadline. \nTo add a deadline task, use the format: deadline <description> /by <deadline>");
                    }
                    String[] words = input.split(" /by ");
                    if (words.length < 2) {
                        throw new SkibidiException("To add a deadline task, use the format: deadline <description> /by <deadline>");
                    } else {
                        Task task = new Deadline(words[0].substring(9).trim(), words[1]);
                        addTask(task);

                    }
                } else if (input.startsWith("event")) {
                    if (!input.contains(" /from ") || !input.contains(" /to ")) {
                        throw new SkibidiException("Error: Event description must include both start and end times. \nTo add an event task, use the format: event <description> /from <start time> /to <end time>");
                    }
                    String[] words = input.split(" /from | /to ");
                    Task task = new Event(words[0].substring(6).trim(), words[1], words[2]);
                    addTask(task);

                } else {
                    System.out.println("I'm sorry, but I don't know what that means :-(");
                }
            } catch (SkibidiException e) {
                System.out.println(e.getMessage());
            }

        }
        scanner.close();
    }
}
