package server.http.request;

import com.sun.net.httpserver.HttpExchange;

public class PutRequestHandler extends RequestHandler {
    @Override
    public void handleRequest(HttpExchange httpExchange) {
        var response = fileCommand.executeCommand(httpExchange);
        if (response != null) {
            ResponseHandler.handleResponse(httpExchange,
                    response,
                    200);
        } else {
            ResponseHandler.handleResponse(httpExchange,
                    "creating the file was forbidden!".getBytes(),
                    403);
        }
    }
}
