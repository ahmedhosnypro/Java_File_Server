package server.file.commander;

import server.file.FileManager;

public class DeleteCommand implements Command {
    public String execute(String... args) {
        String fileName = args[0];
        if (FileManager.getInstance().deleteFile(fileName)) {
            return "the file was successfully deleted!";
        } else {
            return "the file was not found!";
        }
    }
}
