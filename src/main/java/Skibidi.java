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
            System.out.println("Tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i).getDescription());
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
            } else {
                Task task = new Task(input);
                addTask(task.getDescription());
                System.out.println("added:" + toAlternateCaps(input));
            }
        }
        scanner.close();
    }
}
