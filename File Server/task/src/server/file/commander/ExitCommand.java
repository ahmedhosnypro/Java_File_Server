package server.file.commander;

import server.file.FileManager;

public class ExitCommand extends Command {

    @Override
    boolean execute(String fileName) {
        return FileManager.getInstance().deleteAllFiles();
    }
}
