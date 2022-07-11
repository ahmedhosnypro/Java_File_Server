package server.file.commander;

public class FileCommandController {
    private Command command;

    public void setCommand(String type) {
        command = switch (type) {
            case "PUT" -> new PutCommand();
            case "GET" -> new GetCommand();
            case "DELETE" -> new DeleteCommand();
            case "EXIT" -> new ExitCommand();
            default -> throw new IllegalArgumentException("Unknown command");
        };
    }

    public String executeCommand(String... args) {
        return command.execute(args);
    }
}
