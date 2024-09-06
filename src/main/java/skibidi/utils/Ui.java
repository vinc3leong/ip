package skibidi.utils;

import java.util.Scanner;

import skibidi.task.Task;


/**
 * Represents the user interface of the application.
 */
public class Ui {
    public Ui() {
    }
    /**
     * Prints the welcome message.
     */
    public static void printWelcomeMessage() {
        String name = "skibidi";
        System.out.println("Ohio skibidi on the wall, who's the skibidiest of 'em all?!!"
                + "\nHello! I'm " + name + " the skibidiest of 'em all");
    }

    public static void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the list of tasks.
     *
     * @param taskList The list of tasks to be printed.
     */
    public static void printList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.getTask(i).toString());
        }
    }

    /**
     * Prints the list of found tasks.
     *
     * @param foundTasks The list of found tasks to be printed.
     */
    public static void printFoundTasks(TaskList foundTasks) {
        if (foundTasks.size() == 0) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < foundTasks.size(); i++) {
                System.out.println((i + 1) + ". " + foundTasks.getTask(i).toString());
            }
        }

    }

    /**
     * Prints the task after being added to the taskList.
     *
     * @param task The task to be printed after being added to the taskList.
     */
    public static void printAdd(Task task, TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Prints the task after being deleted from the taskList.
     *
     * @param task The task to be printed after being deleted from the taskList.
     */
    public static void printDelete(Task task, TaskList taskList) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Prints the task after being marked as done.
     *
     * @param task The task to be printed after being marked as done.
     */
    public static void printMarkAsDone(Task task) {
        System.out.println("Nice! I've marked this task as done:]");
        System.out.println("  " + task);
    }

    /**
     * Prints the task after being marked as undone.
     *
     * @param task The task to be printed after being marked as undone.
     */
    public static void printMarkAsUndone(Task task) {
        System.out.println("Ok. I've marked this task as not done yet:]");
        System.out.println("  " + task);
    }

    /**
     * Prints the error message when the file is not found.
     */
    public static void printInvalidIndex() {
        System.out.println("Error: invalid index. "
                + "\nPlease enter a valid index from the list of tasks.");
    }

    public static void printInvalidCommand() {
        System.out.println("I'm sorry, but I don't know what that means :-(");
    }

    /**
     * @return the command entered by the user.
     */
    public static String readCommand() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return input;
    }
}
