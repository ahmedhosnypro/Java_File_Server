package server.file;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class FileIdsData {
    private static TreeMap<Integer, String> fileIds = new TreeMap<>(); //file id, file Name
    private static final Gson gson = new Gson();
    static final ReadWriteLock lock = new ReentrantReadWriteLock();
    private static final String DATA_FILE_NAME = "D:\\7-Learn\\Java\\HyperSkill\\File Server\\src\\server\\config\\files_id.json";

    private FileIdsData() {
    }

    public static String getFileName(String fileId) {
        readSyncData();
        try {
            int id = Integer.parseInt(fileId);
            return fileIds.get(id);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private static int getNextId() {
        if (fileIds.isEmpty()) {
            return 1;
        }
        return fileIds.lastKey() + 1;
    }

    public static Integer addNewFileId(String fileName) {
        readSyncData();

        int fileId = getNextId();
        fileIds.put(fileId, fileName);

        Path path = Paths.get(DATA_FILE_NAME);
        try (Writer writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            lock.writeLock().lock();
            gson.toJson(fileIds, writer);
            lock.writeLock().unlock();

            return fileId;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static void readSyncData() {
        lock.readLock().lock();
        fileIds = readData();
        lock.readLock().unlock();
    }

    static TreeMap<Integer, String> readData() {
        Path path = Paths.get(DATA_FILE_NAME);
        try (Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            Map<Integer, String> readData = gson.fromJson(reader,
                    new TypeToken<Map<Integer, String>>() {
                    }.getType());
            if (readData == null) {
                return new TreeMap<>();
            }
            return new TreeMap<>(readData);
        } catch (IOException ignored) {
            //ignored
        }
        return new TreeMap<>();
    }
}
