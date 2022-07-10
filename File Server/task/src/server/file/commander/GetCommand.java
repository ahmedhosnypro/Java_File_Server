package server.file.commander;

import server.file.FileManager;

public class GetCommand extends Command {
    @Override
    boolean execute(String fileName) {
        if (FileManager.getInstance().isExist(fileName)) {
            System.out.println(THE_FILE + fileName + " was sent\n");
            return true;
        } else {
            System.out.println(THE_FILE + fileName + " not found\n");
            return false;
        }
    }
}
