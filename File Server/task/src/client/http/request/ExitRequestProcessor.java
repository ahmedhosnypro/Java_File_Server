package client.http.request;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExitRequestProcessor extends RequestProcessor {

    @Override
    void sendRequest(HttpRequest.Builder httpRequestBuilder, String... args) {
        var httpRequest = httpRequestBuilder.build();
        try {
            HttpResponse<byte[]> response = httpClient.send(
                    httpRequest, HttpResponse.BodyHandlers.ofByteArray());
            if (response.statusCode() == 200) {
                System.out.println("The request was sent.");
            } else {
                System.out.println("server error");
            }
        } catch (IOException | InterruptedException e) {
            //ignore
        }
    }
}
