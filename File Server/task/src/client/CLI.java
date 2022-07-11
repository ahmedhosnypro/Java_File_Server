package client;

import client.http.IHttpClient;

import java.util.Scanner;

public class CLI {
    private CLI() {
    }

    private static final Scanner scanner = new Scanner(System.in);

    static void run() {
        System.out.print("Enter action (1 - get a file, 2 - create a file, 3 - delete a file): ");
        String action = scanner.nextLine();
        if (!isValidAction(action)) {
            return;
        }
        if (action.equals("exit")) {
            IHttpClient.sendRequest("EXIT");
            return;
        }

        System.out.print("Enter file name: ");
        String fileName = scanner.nextLine();

        switch (action) {
            case "1" -> IHttpClient.sendRequest("GET", fileName);
            case "2" -> {
                System.out.print("Enter file content: ");
                String fileContent = scanner.nextLine();

                IHttpClient.sendRequest("PUT", fileName, fileContent);
            }
            case "3" -> IHttpClient.sendRequest("DELETE", fileName);
            default -> throw new IllegalArgumentException("Unknown action");
        }
    }


    private static boolean isValidAction(String action) {
        return action.equals("1") || action.equals("2") || action.equals("3") || action.equals("exit");
    }
}
