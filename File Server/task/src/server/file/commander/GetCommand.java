package server.file.commander;

import server.file.FileManager;

public class GetCommand implements Command {
    @Override
    public String execute(String... args) {
        String fileName = args[0];
        if (FileManager.isExist(fileName)) {
            return "The content of the file is: " + FileManager.getInstance().getFileContent(fileName);
        } else {
            return "the file was not found!";
        }
    }
}
