package server.http;

import com.sun.net.httpserver.HttpServer;
import network.NetConfig;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IHttpServer {
    private IHttpServer() {
    }

    private static final ExecutorService EXECUTOR = Executors.newCachedThreadPool();
    private static HttpServer server;

    public static void startHttpServer() {
        try {
            server = HttpServer.create(new InetSocketAddress(NetConfig.getHostName(), NetConfig.getPort()), 50);
            server.setExecutor(EXECUTOR);
            server.createContext("/", new IHttpHandler());
            server.start();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void stopHttpServer() {
        EXECUTOR.shutdown();
        server.stop(0);
    }
}


