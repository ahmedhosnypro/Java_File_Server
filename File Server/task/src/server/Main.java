package server;

import server.file.ServerFileManager;
import server.http.IHttpServer;

public class Main {

    public static void main(String[] args) {
        ServerFileManager.init();
        IHttpServer.startHttpServer();
    }
}