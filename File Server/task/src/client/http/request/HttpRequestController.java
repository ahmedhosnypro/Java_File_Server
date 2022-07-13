package client.http.request;

import network.NetConfig;

import java.net.URI;
import java.net.http.HttpRequest;

public class HttpRequestController {
     static final String PROTOCOL = "http://";

    private HttpRequestController() {
    }

    public static void sendRequest(String... args) {
        URI uri = URI.create(PROTOCOL +
                NetConfig.getHostName() + ":" +
                NetConfig.getPort() +
                (args.length > 1 ? "?/" + args[1] : "") +
                (args.length > 2 ? "=" + args[2] : ""));

        var httpRequestBuilder = HttpRequest.newBuilder();

        RequestProcessor requestProcessor = switch (args[0]) {
            case "GET" -> {
                httpRequestBuilder
                        .uri(uri)
                        .GET();
                yield new GetRequestProcessor();
            }
            case "DELETE" -> {
                httpRequestBuilder
                        .uri(uri)
                        .DELETE();
                yield new DeleteRequestProcessor();
            }
            case "PUT" -> new PutRequestProcessor();
            case "EXIT" -> {
                httpRequestBuilder
                        .uri(uri)
                        .method("EXIT", HttpRequest.BodyPublishers.noBody())
                        .build();
                yield new ExitRequestProcessor();
            }
            default -> throw new IllegalArgumentException("Unknown command");
        };

        requestProcessor.sendRequest(httpRequestBuilder, args);
    }
}
