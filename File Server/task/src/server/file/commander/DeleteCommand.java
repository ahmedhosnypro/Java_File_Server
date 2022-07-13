package server.file.commander;

import com.sun.net.httpserver.HttpExchange;

public class DeleteCommand extends Command {
    @Override
    byte[] execute(HttpExchange httpExchange) {
        String fileName = getFileName(httpExchange);
        if (fileName == null) {
            return null;
        }

        if (fileManager.deleteFile(fileName)) {
            return "this file was deleted successfully!".getBytes();
        } else {
            return null;
        }
    }
}
