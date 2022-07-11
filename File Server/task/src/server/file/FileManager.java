package server.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {
    private static FileManager instance = new FileManager();
    private static final String NOT_FOUND = "the file was not found!";

    private FileManager() {
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

    public static Path getFilePath(String fileName) {
        return Path.of(
                System.getProperty("user.dir"),
                "src", "server", "data", fileName
        );
    }

    public static FileManager getInstance() {
        if (instance == null) {
            instance = new FileManager();
        }
        return instance;
    }

    private static boolean isWrongFileName(String fileName) {
        try {
            Paths.get(fileName);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    public static boolean isExist(String fileName) {
        if (isWrongFileName(fileName)) {
            return false;
        }
        File file = new File(getFilePath(fileName).toString());
        return file.exists();
    }

    public boolean deleteFile(String fileName) {
        if (isWrongFileName(getFilePath(fileName).toString())) {
            return false;
        }
        File file = new File(getFilePath(fileName).toString());
        if (file.exists()) {
            try {
                Files.delete(file.toPath());
                return true;
            } catch (Exception x) {
                return false;
            }
        }
        return false;
    }

    public boolean createFile(String fileName) {
        if (isWrongFileName(fileName)) {
            return false;
        }
        File file = new File(getFilePath(fileName).toString());
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    return true;
                }
            } catch (Exception e) {
                System.out.println("Could not create file " + fileName);
            }
        }
        return false;
    }

    public String getFileContent(String fileName) {
        if (isWrongFileName(fileName)) {
            return NOT_FOUND;
        }
        File file = new File(getFilePath(fileName).toString());
        if (file.exists()) {
            try {
                return new String(Files.readAllBytes(file.toPath()));
            } catch (Exception e) {
                //ignore
            }
        }
        return NOT_FOUND;
    }

    public void init() {
        getInstance();
    }
}
