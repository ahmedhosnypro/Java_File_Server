package server;

import server.file.FileManager;
import server.http.IHttpServer;

public class Main {

    public static void main(String[] args) {
        FileManager.getInstance().init();
        IHttpServer.startHttpServer();
    }
}