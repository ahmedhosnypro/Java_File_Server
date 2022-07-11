package server.http;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class PutRequestHandler extends RequestHandler {
    @Override
    public void handleRequest(HttpExchange httpExchange) {
        String fileName = httpExchange.
                getRequestURI()
                .toString()
                .split("\\?")[1]
                .split("=")[1];

        try {
            String fileContent = new String(httpExchange.getRequestBody().readAllBytes());
            ResponseHandler.handleResponse(httpExchange,
                    fileCommand.executeCommand(fileName, fileContent),
                    200);
        } catch (IOException e) {
            ResponseHandler.handleResponse(httpExchange, "creating the file was forbidden!", 403);
        }
    }
}
