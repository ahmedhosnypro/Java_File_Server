package client.http;

import server.ServerConfig;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class IHttpClient {
    private IHttpClient() {
    }

    private static final String PROTOCOL = "http://";

    public static void sendRequest(String... args) {
        HttpClient httpClient = HttpClient.newHttpClient();

        URI uri = URI.create(PROTOCOL +
                ServerConfig.getHostName() + ":" +
                ServerConfig.getPort() +
                (args.length > 1 ? "?/fileName=" + args[1] : ""));

        HttpRequest request = createHttpRequest(uri, args);

        try {
            HttpResponse<String> response = httpClient.send(
                    request, HttpResponse.BodyHandlers.ofString());

            System.out.println("The response says that " + response.body());
        } catch (IOException | InterruptedException e) {
            //ignore
        }
    }

    private static HttpRequest createHttpRequest(URI uri, String... args) {
        var requestBuilder = HttpRequest.newBuilder()
                .uri(uri);
        return switch (args[0]) {
            case "GET" -> requestBuilder.GET()
                    .build();
            case "DELETE" -> requestBuilder.DELETE()
                    .build();
            case "PUT" -> requestBuilder.PUT(HttpRequest.BodyPublishers.ofString(args[2]))
                    .header("Content-Type", "plain/text")
                    .build();
            case "EXIT" -> requestBuilder
                    .method("EXIT", HttpRequest.BodyPublishers.noBody())
                    .build();
            default -> throw new IllegalArgumentException("Unknown command");
        };
    }
}
