package server.file.commander;

import com.sun.net.httpserver.HttpExchange;
import server.file.FileIdsData;
import server.file.ServerFileManager;
import file.FileManager;

public abstract class Command {
    FileManager fileManager = ServerFileManager.getInstance();

    abstract byte[] execute(HttpExchange httpExchange);

    String getFileName(HttpExchange httpExchange) {
        var query = httpExchange.getRequestURI().getQuery().split("//")[0].split("=");
        String fileQueryType = query[0];
        String fileQueryValue = query[1]; //file name or id

        String fileName = fileQueryValue;

        if (fileQueryType.equals("/id")) {
            fileName = FileIdsData.getFileName(fileQueryValue);
            if (fileName == null) {
                return null;
            }
        }
        return fileName;
    }
}
