package client.http;

import client.http.request.HttpRequestController;

public class IHttpClient {
    private IHttpClient() {
    }

    public static void sendRequest(String... args) {
        HttpRequestController.sendRequest(args);
    }
}
