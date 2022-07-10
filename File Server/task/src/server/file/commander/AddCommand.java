package server.file.commander;

import server.file.FileManager;

public class AddCommand extends Command {
    public boolean execute(String fileName) {
        if (FileManager.getInstance().createFile(fileName)) {
            System.out.println(THE_FILE + fileName + " added successfully\n");
            return true;
        }
        System.out.println("Cannot add " + THE_FILE2 + fileName + "\n");
        return false;
    }
}
