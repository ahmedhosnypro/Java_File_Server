package server.file.commander;

public class Controller {
    private Command command;

    public void setCommand(String type) {
        command = switch (type) {
            case "add" -> new AddCommand();
            case "get" -> new GetCommand();
            case "delete" -> new DeleteCommand();
            case "exit" -> new ExitCommand();
            default -> throw new IllegalArgumentException("Unknown command");
        };
    }

    public boolean executeCommand(String fileName) {
        return command.execute(fileName);
    }
}
