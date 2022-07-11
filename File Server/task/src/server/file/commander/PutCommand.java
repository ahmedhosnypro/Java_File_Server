package server.file.commander;

import server.file.FileManager;

import java.io.FileWriter;
import java.io.IOException;

public class PutCommand implements Command {
    public String execute(String... args) {
        String fileName = args[0];
        if (FileManager.getInstance().createFile(fileName)) {
            try (FileWriter writer = new FileWriter(FileManager.getFilePath(fileName).toString())) {
                writer.write(args[1]);
                return "the file was created!";
            } catch (IOException e) {
                //ignored
            }
        }
        return "creating the file was forbidden!";
    }
}
