package server.file.commander;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

import static server.file.FileIdsData.addNewFileId;

public class PutCommand extends Command {
    public byte[] execute(HttpExchange httpExchange) {
        String fileName = httpExchange.
                getRequestURI()
                .getQuery()
                .split("=")[1];

        try {
            var fileContent = httpExchange.getRequestBody().readAllBytes();
            if (fileManager.saveFile(fileName, fileContent)) {
                var fileId = addNewFileId(fileName);
                if (fileId != null) {
                    return ("file is saved! ID = " + fileId).getBytes();
                }
            }
        } catch (IOException e) {
            //ignore
        }

        return null;
    }
}
