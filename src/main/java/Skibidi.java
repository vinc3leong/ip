import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Skibidi {

    private static List<Task> tasks = new ArrayList<>();

    public static void addTask(Task task) {
        tasks.add(task);
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
    public static void main(String[] args) {
        String name = "Skibidi";
        System.out.println(
                " ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣤⣴⣤⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⣰⣾⣿⣿⣿⣿⣿⣿⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⢠⡿⠋⠉⠉⠛⠛⠛⠋⠉⠙⢿⡆⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⣼⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣧⠀⠀⠀⠀⠀⠀\n" +
                        "⡰⠉⠉⠁⠉⡙⠹⢠⢾⣛⠛⢶⢀⡶⠛⣛⠳⡄⡏⢋⠉⠉⠉⠉⢢\n" +
                        "⢹⠶⠶⠶⣾⠡⣾⠈⠸⡿⠷⠀⠀⠀⢾⣿⠇⠁⡶⡌⢷⠶⠶⠶⡏\n" +
                        "⢸⠀⠀⠀⠆⠀⢻⡀⠀⠀⡀⠀⠀⠀⢀⢀⡀⠀⡟⠀⠸⡀⠀⠀⡇\n" +
                        "⢸⠀⠀⢸⠀⠀⠈⡇⣠⠒⠓⠤⣀⠤⠘⠀⡘⢰⠃⠀⠀⡇⠀⠀⡇\n" +
                        "⢸⠀⠀⡎⠀⠀⠀⢻⠀⠙⣶⣶⣒⣶⣶⠋⢀⡏⠀⠀⠀⢸⠀⠀⡇\n" +
                        "⢸⠀⠀⡇⠀⠀⠀⠘⣧⡀⠈⠿⣿⡿⠁⢀⢮⠃⠀⠀⠀⢸⠀⠀⡇\n" +
                        "⢸⠀⠀⡇⠀⠀⠀⠀⢰⠑⠄⣀⠀⢀⡠⠊⡌⠀⠀⠀⠀⢸⠀⠀⡇\n" +
                        "⢸⠀⠀⠘⢄⠀⠀⠀⠀⠆⠀⠀⠀⠀⠀⠰⠀⠀⠀⠀⡠⠃⠀⠀⡇\n" +
                        "⠈⠦⣀⣔⠂⠋⠒⠲⠶⠾⠤⠤⠤⠤⠤⠷⠶⠖⠒⠉⠒⢢⣀⠴⠃\n" +
                        "⠀⠀⠀⠅⠉⠉⠉⠉⠉⠒⠒⠒⠒⠒⠒⠊⠉⠉⠉⠉⠉⠨⡀⠀⠀\n" +
                        "⠀⠀⠀⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠁⠀⠀\n" +
                        "⠀⠀⠀⠳⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡜⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠱⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠎⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠈⠢⢄⣀⡀⢀⠀⡀⢀⠀⣀⣀⡠⠔⠁⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⡌⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⢀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⡀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠸⠤⠠⠀⢀⣀⣀⣀⠀⠀⠤⠤⠖⠀⠀⠀⠀⠀⠀⠀"
        );
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
                        System.out.println(tasks.get(taskNumber - 1).toString());
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
                        System.out.println(tasks.get(taskNumber - 1).toString());
                    } else {
                        System.out.println("Error: Task number " + taskNumber + " is out of bounds.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Task number must be an integer.");
                }
            } else if (input.startsWith("todo")) {
                Task task = new Todo(input.substring(5).trim());
                addTask(task);
                System.out.println("Got it. I've added this task:");
                System.out.println(task.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");

            } else if (input.startsWith("deadline")) {
                String[] words = input.split(" /by ");
                if (words.length < 2) {
                    System.out.println("Error: Deadline description must include a deadline.");
                } else {
                    Task task = new Deadline(words[0].substring(9).trim(), words[1]);
                    addTask(task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(task.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                }
            } else if (input.startsWith("event")) {
                String[] words = input.split(" /from | /to ");
                if (words.length < 2) {
                    System.out.println("Error: Event description must include both start and end times.");
                } else {
                    Task task = new Event(words[0].substring(6).trim(), words[1], words[2]);
                    addTask(task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(task.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                }

            } else {
                System.out.println("I'm sorry, but I don't know what that means :-(");
            }
        }
        scanner.close();
    }
}
