package network;
//implementation 'com.google.code.gson:gson:2.8.6'

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NetConfig {
    private static NetConfig instance;
    private final String address;
    private final int port;
    private static final String CONFIG_FILE_NAME = "D:\\7-Learn\\Java\\HyperSkill\\File Server\\src\\server\\config\\server_conf.json";

    private NetConfig(String address, int port) {
        this.address = address;
        this.port = port;
    }

    private static void initialize() {
        Path path = Paths.get(CONFIG_FILE_NAME);
        if (instance == null) {
            try (Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
                Gson gson = new Gson();
                instance = gson.fromJson(reader, NetConfig.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getHostName() {
        initialize();
        return instance.address;
    }

    public static int getPort() {
        initialize();
        return instance.port;
    }
}