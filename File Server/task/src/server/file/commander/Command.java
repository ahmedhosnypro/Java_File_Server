package server.file.commander;

public abstract class Command {
    final String THE_FILE = "The file ";
    final String THE_FILE2 = "the file ";

    abstract boolean execute(String fileName);
}
