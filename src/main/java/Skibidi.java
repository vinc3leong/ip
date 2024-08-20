import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Skibidi {
    public static String toAlternateCaps(String input) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (i % 2 == 0) {
                result.append(Character.toUpperCase(c));
            } else {
                result.append(Character.toLowerCase(c));
            }
        }
        return result.toString();
    }

    private static List<Task> tasks = new ArrayList<>();

    public static void addTask(String taskDescription) {
        tasks.add(new Task(taskDescription));
    }

    public static void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).isDone) {
                    System.out.println((i + 1) + ". " +"[X] " + tasks.get(i).getDescription());
                } else {
                    System.out.println((i + 1) + ". " +"[ ] " + tasks.get(i).getDescription());
                }
            }
        }
    }
    public static void main(String[] args) {
        String name = "Skibidi";
        System.out.println("Ohio skibidi on the wall, who's the skibidiest of 'em all?!! " +
                "\nHello! I'm " + name + " the skibidiest of 'em all"+ " \nWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (Objects.equals(input.trim(), "bye")) {
                System.out.println("Bye! Hope to never see you again soon!");
                break;
            } else if (Objects.equals(input.trim(), "list")) {
                listTasks();
            } else if (input.startsWith("mark")) {
                String[] words = input.split(" ");
                try {
                    int taskNumber = Integer.parseInt(words[1]);
                    if (taskNumber > 0 && taskNumber <= tasks.size()) {
                        tasks.get(taskNumber - 1).markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("[X] " + tasks.get(taskNumber - 1).getDescription());
                    } else {
                        System.out.println("Error: Task number " + taskNumber + " is out of bounds.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Task number must be an integer.");
                }

            } else if (input.startsWith("unmark")) {
                String[] words = input.split(" ");
                try {
                    int taskNumber = Integer.parseInt(words[1]);
                    if (taskNumber > 0 && taskNumber <= tasks.size()) {
                        tasks.get(taskNumber - 1).markAsNotDone();
                        System.out.println("Ok, I've marked this task as not done yet:");
                        System.out.println("[ ] " + tasks.get(taskNumber - 1).getDescription());
                    } else {
                        System.out.println("Error: Task number " + taskNumber + " is out of bounds.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Task number must be an integer.");
                }
            } else {
                Task task = new Task(input);
                addTask(task.getDescription());
                System.out.println("added: " + toAlternateCaps(input));
            }
        }
        scanner.close();
    }
}
