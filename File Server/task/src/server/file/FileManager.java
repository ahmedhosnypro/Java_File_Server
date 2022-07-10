package server.file;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

public class FileManager {
    private static FileManager instance = new FileManager();
    private static final List<String> fileNames = List.of("file1", "file2", "file3",
            "file4", "file5", "file6", "file7",
            "file8", "file9", "file10");

    private FileManager() {
    }

    public static FileManager getInstance() {
        if (instance == null) {
            instance = new FileManager();
        }
        return instance;
    }

    private boolean isWrongFileName(String fileName) {
        return !fileName.matches("file[1-9]|file[1-9]0");
    }

    public boolean isExist(String fileName) {
        if (isWrongFileName(fileName)) {
            return false;
        }
        File file = new File(fileName);
        return file.exists();
    }

    public boolean deleteFile(String fileName) {
        if (isWrongFileName(fileName)) {
            return false;
        }
        File file = new File(fileName);
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
        File file = new File(fileName);
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

    public boolean deleteAllFiles() {
        for (var file : fileNames) {
            deleteFile(file);
        }
        return true;
    }
}
