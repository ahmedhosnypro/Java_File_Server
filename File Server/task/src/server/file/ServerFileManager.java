package server.file;

import file.FileManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ServerFileManager implements FileManager {
    private static ServerFileManager instance;

    private ServerFileManager() {
        Path dataRootPath = Path.of(
                System.getProperty("user.dir"),
                "src", "server", "data"
        );
        if (!isExist("")) {
            try {
                Files.createDirectories(dataRootPath);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    public static ServerFileManager getInstance() {
        if (instance == null) {
            instance = new ServerFileManager();
        }
        return instance;
    }

    public static void init() {
        getInstance();
    }

    @Override
    public boolean isExist(String fileName) {
        if (FileManager.isWrongFileName(fileName)) {
            return false;
        }
        File file = new File(getFilePath(fileName).toString());
        return file.exists();
    }

    @Override
    public Path getFilePath(String fileName) {
        return Path.of(
                System.getProperty("user.dir"),
                "src", "server", "data", fileName
        );
    }
}
