package server.file.commander;

import com.sun.net.httpserver.HttpExchange;

public class ExitCommand extends Command {

    @Override
    byte[] execute(HttpExchange httpExchange) {
        return new byte[0];
    }
}
