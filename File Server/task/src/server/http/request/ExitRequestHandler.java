package server.http.request;

import com.sun.net.httpserver.HttpExchange;
import server.http.IHttpServer;

public class ExitRequestHandler extends RequestHandler {
    @Override
    public void handleRequest(HttpExchange httpExchange) {
        ResponseHandler.handleResponse(httpExchange,
                "Server is shutting down".getBytes(),
                200);
        IHttpServer.stopHttpServer();
    }
}
