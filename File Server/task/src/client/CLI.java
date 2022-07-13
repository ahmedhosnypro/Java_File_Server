package client;

import client.http.IHttpClient;

import java.util.Scanner;

public class CLI {
    private CLI() {
    }

    private static final Scanner scanner = new Scanner(System.in);
    private static final client.cli.Controller controller = new client.cli.Controller();

    static void run() {
        System.out.print("Enter action (1 - get a file, 2 - save a file, 3 - delete a file): ");
        String action = scanner.nextLine();
        if (!isValidAction(action)) {
            return;
        }
        if (action.equals("exit")) {
            IHttpClient.sendRequest("EXIT");
            return;
        }

        switch (action) {
            case "1" -> controller.executeCommand("get");
            case "2" -> controller.executeCommand("put");
            case "3" -> controller.executeCommand("delete");
            default -> throw new IllegalArgumentException("Unknown action");
        }
    }


    private static boolean isValidAction(String action) {
        return action.equals("1") || action.equals("2") || action.equals("3") || action.equals("exit");
    }
}
