package Skibidi;

import java.io.IOException;

abstract class Command {

    public Command(String command) {
    }
    public abstract void execute(Storage storage, TaskList taskList) throws IOException, SkibidiException;

    public boolean isExit() {
        return false;
    }
}
