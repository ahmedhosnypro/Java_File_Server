package server.http.request;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class ResponseHandler {
    private ResponseHandler() {
    }

    public static void handleResponse(HttpExchange httpExchange, byte[] response, int responseCode) {
        OutputStream outputStream = httpExchange.getResponseBody();
        try {
            httpExchange.sendResponseHeaders(responseCode, response.length);
            outputStream.write(response);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Error while sending response");
        }
    }
}
