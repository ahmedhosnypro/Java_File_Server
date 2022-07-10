package server.file.commander;

import server.file.FileManager;

public class DeleteCommand extends Command {
    public boolean execute(String fileName) {
        if (FileManager.getInstance().deleteFile(fileName)) {
            System.out.println(THE_FILE + fileName + " was deleted\n");
            return true;
        } else {
            System.out.println(THE_FILE + fileName + " not found\n");
            return false;
        }
    }
}
