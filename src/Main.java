import game.GameCenter;
import server.Client;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
//        GameCenter gameCenter = new GameCenter();
//        gameCenter.play();

        Client client = new Client();
        try {
            client.test();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
