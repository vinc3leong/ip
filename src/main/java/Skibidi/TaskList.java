package Skibidi;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    public int size() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void markTaskAsDone(int index) {
        tasks.get(index).markAsDone();
    }

    public void unmarkTaskAsDone(int index) {
        tasks.get(index).markAsNotDone();
    }
}
