package client;

import client.file.ClientFileManager;

public class Main {

    public static void main(String[] args) {
        ClientFileManager.getInstance();
        CLI.run();
    }
}
