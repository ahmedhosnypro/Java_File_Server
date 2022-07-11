package server.http;

import com.sun.net.httpserver.HttpExchange;

public class RequestHandlerBuilder {
    private RequestHandlerBuilder() {
    }

    public static RequestHandler create(HttpExchange httpExchange) {
        String requestMethod = httpExchange.getRequestMethod();
        RequestHandler requestHandler = switch (requestMethod) {
            case "GET" -> new GetRequestHandler();
            case "PUT" -> new PutRequestHandler();
            case "DELETE" -> new DeleteRequestHandler();
            case "EXIT" -> new ExitRequestHandler();
            default -> throw new IllegalArgumentException("Can't handle " + requestMethod + " request method");
        };
        requestHandler.setFileCommand(requestMethod);
        return requestHandler;
    }
}
