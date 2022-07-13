package client.file;

import file.FileManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ClientFileManager implements FileManager {
    private static ClientFileManager instance;


    private ClientFileManager() {
        Path dataRootPath = Path.of(
                System.getProperty("user.dir"),
                "src", "client", "data"
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

    public static ClientFileManager getInstance() {
        if (instance == null) {
            instance = new ClientFileManager();
        }
        return instance;
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
                "src", "client", "data", fileName
        );
    }
}
