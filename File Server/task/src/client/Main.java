package client;

public class Main {

    public static void main(String[] args) {
        ClientStream.send("Give me everything you have!");
        ClientStream.receive();

    }
}
