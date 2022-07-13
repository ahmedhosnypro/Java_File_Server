package client.cli;

import client.http.IHttpClient;

public class DeleteCommand extends Command {
    @Override
    void execute() {
        String[] args = getFileQueryArgs();
        IHttpClient.sendRequest("DELETE", args[0], args[1]);
    }
}
