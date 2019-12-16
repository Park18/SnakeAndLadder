import game.GameCenter;
//import server.Client;


public class Main {
    public static void main(String[] args) {
        GameCenter gameCenter = new GameCenter();
        gameCenter.play();
        gameCenter.printRanking();
    }
}
