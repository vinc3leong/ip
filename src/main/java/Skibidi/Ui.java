package Skibidi;

import java.util.Scanner;

public class Ui {
    public Ui() {
    }
    public static void printWelcomeMessage() {
        String name = "Skibidi";
        System.out.println("Ohio skibidi on the wall, who's the skibidiest of 'em all?!!" +
                "\nHello! I'm " + name + " the skibidiest of 'em all");
    }

    public static void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.getTask(i).toString());
        }
    }

    public static void printAdd(Task task, TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public static void printDelete(Task task, TaskList taskList) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public static void printMarkAsDone(Task task) {
        System.out.println("Nice! I've marked this task as done:]");
        System.out.println("  " + task);
    }

    public static void printMarkAsUndone(Task task) {
        System.out.println("Ok. I've marked this task as not done yet:]");
        System.out.println("  " + task);
    }

    public static void printInvalidIndex() {
        System.out.println("Error: invalid index. " +
                "\nPlease enter a valid index from the list of tasks.");
    }

    public static void printInvalidCommand() {
        System.out.println("I'm sorry, but I don't know what that means :-(");
    }

    public static String readCommand() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return input;
    }
}
