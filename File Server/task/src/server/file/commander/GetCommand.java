package server.file.commander;

import com.sun.net.httpserver.HttpExchange;

public class GetCommand extends Command {

    @Override
    byte[] execute(HttpExchange httpExchange) {
        String fileName = getFileName(httpExchange);
        if (fileName == null) {
            return null;
        }

        if (fileManager.isExist(fileName)) {
            return fileManager.getFileContent(fileName);
        } else {
            return null;
        }
    }
}
