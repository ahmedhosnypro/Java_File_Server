package client.http.request;

import client.file.ClientFileManager;
import network.NetConfig;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static client.http.request.HttpRequestController.PROTOCOL;

public class PutRequestProcessor extends RequestProcessor {
    @Override
    void sendRequest(HttpRequest.Builder httpRequestBuilder, String... args) {
        URI uri = URI.create(PROTOCOL +
                NetConfig.getHostName() + ":" +
                NetConfig.getPort() +
                (args.length > 1 ? "?/name" : "") +
                (args.length > 2 ? "=" + args[2] : ""));

        var fileContent = ClientFileManager.getInstance().getFileContent(args[1]);
        if (fileContent == null) {
            System.out.println("File not found!");
            return;
        }
        var httpRequest = httpRequestBuilder
                .uri(uri)
                .PUT(HttpRequest.BodyPublishers.ofByteArray(fileContent))
                .header("Content-Type", "application/octet-stream")
                .build();
        try {
            HttpResponse<byte[]> response = httpClient.send(
                    httpRequest, HttpResponse.BodyHandlers.ofByteArray());
            System.out.println(RESPONSE_PREFIX + new String(response.body()));
        } catch (IOException | InterruptedException e) {
            System.out.println("Error while sending request!");
        }

    }
}
