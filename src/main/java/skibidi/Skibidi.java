package skibidi;

import java.time.format.DateTimeParseException;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Represents the main class of the Skibidi program.
 */
public class Skibidi {

    private static TaskList tasks = new TaskList();
    private Storage storage;

    public Skibidi(String filePath) {
         storage = new Storage(filePath);
        try {
            Ui.printWelcomeMessage();
            tasks = storage.load();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (SkibidiException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    /**
     * Runs the Skibidi program.
     */
    public void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = Ui.readCommand();
                Command c = Parser.parse(command);
                c.execute(storage, tasks);
                isExit = c.isExit();
            } catch (DateTimeParseException e) {
                System.out.println("Error: Invalid date format. Please enter date in yyyy-mm-dd format.");
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Error: Task number must be an integer.");
            } catch (SkibidiException e) {
                System.out.println(e.getMessage());
            }

        }
    }


    public static void main(String[] args) {
        new Skibidi("src/main/data/skibidi.txt").run();
    }
}
