package client.http.request;

import java.net.http.HttpRequest;

public class DeleteRequestProcessor extends RequestProcessor {
    @Override
    void sendRequest(HttpRequest.Builder request, String... args) {
        super.sendRequest(request);
    }
}
