package server.http.request;

import com.sun.net.httpserver.HttpExchange;
import server.file.commander.FileCommandController;

public abstract class RequestHandler {
    FileCommandController fileCommand = new FileCommandController();

    public void handleRequest(HttpExchange httpExchange) {
        var response = fileCommand.executeCommand(httpExchange);
        if (response != null) {
            ResponseHandler.handleResponse(httpExchange, response, 200);
        } else {
            sendError(httpExchange);
        }
    }

    private static void sendError(HttpExchange httpExchange) {
        ResponseHandler.handleResponse(httpExchange,
                "this file is not found!".getBytes(),
                404);
    }

    public void setFileCommand(String fileCommandType) {
        this.fileCommand.setCommand(fileCommandType);
    }
}
