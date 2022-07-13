package client.cli;

public class Controller {
    private Command command;

    public void executeCommand(String command) {
        this.command = switch (command) {
            case "get" -> new GetCommand();
            case "put" -> new PutCommand();
            case "delete" -> new DeleteCommand();
            default -> throw new IllegalArgumentException("Unknown command");
        };
        this.command.execute();
    }

    public void setCommand(Command command) {
        this.command = command;
    }
}
