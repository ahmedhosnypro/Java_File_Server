package server;

import server.file.commander.FileCommandController;

import java.util.Scanner;

public class FileServer {
    private static final FileCommandController controller = new FileCommandController();
    private static final Scanner scanner = new Scanner(System.in);

    private FileServer() {
    }

    public static void start() {
        boolean isRunning = true;
        while (isRunning) {
            isRunning = runCommand();
        }

    }

    public static boolean runCommand() {
        var commandArgs = scanner.nextLine().trim().split(" ");
        switch (commandArgs[0]) {
            case "add", "get", "delete" -> {
                if (commandArgs.length < 2) {
                    System.out.println("Invalid number of arguments");
                    return true;
                }
            }
            default -> {
                if (commandArgs.length > 1) {
                    System.out.println("Invalid number of arguments");
                    return true;
                }
            }
        }
        controller.setCommand(commandArgs[0]);
        if (commandArgs[0].equals("exit")) {
            controller.executeCommand(commandArgs[0]);
            return false;
        }
        controller.executeCommand(commandArgs[1]);
        return true;
    }
}