package server.http;

import com.sun.net.httpserver.HttpExchange;
import server.file.commander.FileCommandController;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

public abstract class RequestHandler {
    FileCommandController fileCommand = new FileCommandController();

    void handleRequest(HttpExchange httpExchange) {
        String fileName = httpExchange.
                getRequestURI()
                .toString()
                .split("\\?")[1]
                .split("=")[1];

        ResponseHandler.handleResponse(httpExchange,
                fileCommand.executeCommand(fileName),
                200);
    }

    public void setFileCommand(String fileCommandType) {
        this.fileCommand.setCommand(fileCommandType);
    }
}
