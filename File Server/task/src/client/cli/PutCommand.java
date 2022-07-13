package client.cli;

import client.http.IHttpClient;

public class PutCommand extends Command {
    @Override
    void execute() {
        String[] args = getFileQueryArgs();
        IHttpClient.sendRequest("PUT", args[0], args[1]); // args[0] = file name, args[1] = server file name
    }

    @Override
    String[] getFileQueryArgs() {
        System.out.print("Enter name of the file: ");
        String fileName = scanner.nextLine();

        System.out.print("Enter name of the file to be saved on server: ");
        String serverFileName = scanner.nextLine();

        if (serverFileName.equals("")) {
            serverFileName = fileName;
        }
        return new String[]{fileName, serverFileName};
    }
}
