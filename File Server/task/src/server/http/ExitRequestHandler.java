package server.http;

import com.sun.net.httpserver.HttpExchange;

public class ExitRequestHandler extends RequestHandler {
    @Override
    public void handleRequest(HttpExchange httpExchange) {
        IHttpServer.stopHttpServer();
    }
}
