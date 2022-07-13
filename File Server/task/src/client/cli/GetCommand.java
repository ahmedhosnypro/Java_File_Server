package client.cli;

import client.http.IHttpClient;

public class GetCommand extends Command {
    @Override
    public void execute() {
        String[] args = getFileQueryArgs();
        IHttpClient.sendRequest("GET", args[0], args[1]);
    }
}
