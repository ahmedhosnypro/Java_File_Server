package client.cli;

import java.util.Scanner;

public abstract class Command {
    protected Scanner scanner = new Scanner(System.in);

    abstract void execute();

    String[] getFileQueryArgs() {
        System.out.print("Do you want to get the file by name or by id (1 - name, 2 - id): ");
        String choice = scanner.nextLine();

        String fileQueryType = "";
        String fileQueryValue = "";

        if (choice.equals("1")) {
            fileQueryType = "name";
            System.out.print("Enter file name: ");
        } else if (choice.equals("2")) {
            fileQueryType = "id";
            System.out.print("Enter id: ");
        } else {
            throw new IllegalArgumentException("Invalid choice");
        }

        fileQueryValue = scanner.nextLine();
        return new String[]{fileQueryType, fileQueryValue};
    }
}
