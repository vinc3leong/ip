package Skibidi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < taskList.size(); i++) {
            fw.write(taskList.getTask(i).writeToFile() + "\n");
        }
        fw.close();
    }

    public TaskList load() throws FileNotFoundException, SkibidiException {
        TaskList taskList = new TaskList();
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            Task task = Parser.parseTask(line);
            taskList.addTask(task);
        }
        Ui.printList(taskList);
        return taskList;
    }
}
