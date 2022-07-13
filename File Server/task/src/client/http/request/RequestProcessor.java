package client.http.request;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public abstract class RequestProcessor {
    HttpClient httpClient = HttpClient.newHttpClient();
    static final Scanner scanner = new Scanner(System.in);
    static final String RESPONSE_PREFIX = "Response says that ";
    static final String THT_RESPONSE_PREFIX = "The response says that ";

    void sendRequest(HttpRequest.Builder httpRequestBuilder, String... args) {
        var httpRequest = httpRequestBuilder.build();
        try {
            HttpResponse<byte[]> response = httpClient.send(
                    httpRequest, HttpResponse.BodyHandlers.ofByteArray());
            System.out.println(THT_RESPONSE_PREFIX + new String(response.body()));
        } catch (IOException | InterruptedException e) {
            //ignore
        }
    }
}
