package server.http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class IHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) {
        RequestHandler requestHandler = RequestHandlerBuilder.create(httpExchange);
        requestHandler.handleRequest(httpExchange);
    }
}