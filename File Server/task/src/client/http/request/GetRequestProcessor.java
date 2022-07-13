package client.http.request;

import client.file.ClientFileManager;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GetRequestProcessor extends RequestProcessor {

    @Override
    void sendRequest(HttpRequest.Builder httpRequestBuilder, String... args) {
        var httpRequest = httpRequestBuilder.build();
        try {
            // binary response
            HttpResponse<byte[]> response = httpClient.send(
                    httpRequest, HttpResponse.BodyHandlers.ofByteArray());
            switch (response.statusCode()) {
                case 200 -> {
                    System.out.print("The file was downloaded! Specify a name for it: ");
                    String fileName = scanner.nextLine();

                    if (ClientFileManager.getInstance().saveFile(fileName, response.body())) {
                        System.out.println("File saved on the hard drive!");
                    } else {
                        System.out.println("The file was not saved!");
                    }
                }
                case 404 -> System.out.println(THT_RESPONSE_PREFIX + new String(response.body()));
                default -> System.out.println("unknown error");
            }
        } catch (IOException | InterruptedException e) {
            //ignore
        }
    }
}
