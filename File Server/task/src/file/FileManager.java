package file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public interface FileManager {

    static boolean isWrongFileName(String fileName) {
        try {
            Paths.get(fileName);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    boolean isExist(String fileName);

    Path getFilePath(String fileName);

    default boolean deleteFile(String fileName) {
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

    default boolean createFile(String fileName) {
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

    default boolean saveFile(String fileName, byte[] fileContent) {
        if (createFile(fileName)) {
            try {
                Files.write(getFilePath(fileName), fileContent);
                return true;
            } catch (IOException e) {
                //ignore
            }
        }
        return false;
    }

    default byte[] getFileContent(String fileName) {
        if (isWrongFileName(fileName)) {
            return null;
        }
        File file = new File(getFilePath(fileName).toString());
        if (file.exists()) {
            try {
                return Files.readAllBytes(file.toPath());
            } catch (Exception e) {
                //ignore
            }
        }
        return null;
    }
}
