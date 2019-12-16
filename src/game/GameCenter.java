package game;

import game.player.BasePlayer;
import game.player.ComputerPlayer;
import game.player.UserPlayer;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * ������ �߾�ó�� �޼���
 * ���� ������ �߾�ó��
 */
public class GameCenter {
    /*------------------------------------------------------------
     * ����
     *------------------------------------------------------------*/
    private static int rankding = 0;                    // �÷��̾� ��ŷ

    private BasePlayer[] players;                       // �÷��̾� ���� �迭
    private int playerCount;                            // �÷��̾� �ο���
    private GameBoard[] gameBoard = new GameBoard[100]; // ���� ������ (ũ�� 100)
    private Scanner scanner = new Scanner(System.in);   // Console �ڿ�
    private Command command = new Command();            // ��ɾ� ��ü

    /*------------------------------------------------------------
     * �޼ҵ�
     *------------------------------------------------------------*/

    /**
     * ������
     * player, gameBoard �ʱ�ȭ
     */
    public GameCenter(){
        playerInit();
        gameBoardInit();
    }

    /**
     * �÷��̾� �ʱ�ȭ �޼ҵ�
     */
    public void playerInit() {
        this.playerCount = playerCountInit();

        Random random = new Random();
        int userIndex = random.nextInt(this.playerCount);
        System.out.println("[PlayerID] : Player[" + userIndex + "]");

        this.players = new BasePlayer[this.playerCount];
        for(int playerID = 0; playerID < this.playerCount; playerID++){
            if(playerID == userIndex)
                this.players[playerID] = new UserPlayer(playerID);

            else
                this.players[playerID] = new ComputerPlayer(playerID);
        }
    }

    /**
     * �� ó�� ���� ����� �ο��� ���ϴ� �޼ҵ�
     * @return ���� �ο�
     */
    public int playerCountInit(){
        System.out.println("�Բ� ������ �ο��� �Է����ּ���.(�ּ� 2�� / �ִ� 6��)");

        while(true){
            System.out.print("[�ο�] >> ");
            int playerCount = 0;

            if(scanner.hasNextInt())
                playerCount = scanner.nextInt();

            else{
                System.out.println("���ڰ� �ƴմϴ�. �ٽ� �Է����ּ���.");
                scanner.nextLine();
                continue;
            }

            if(playerCount < 2 || playerCount > 6)
                System.out.println("�߸��� ������ �Է��߽��ϴ�. �ٽ� �Է��� �ּ���.");

            else
                return playerCount;
        }
    }

    /**
     * �������� �ʱ�ȭ�ϴ� �޼ҵ�
     */
    public void gameBoardInit(){
        for(int pos = 1; pos <= 100; pos++)
            this.gameBoard[pos - 1] = new GameBoard(pos);

        this.gameBoard[4].setMovePos(16);
        this.gameBoard[8].setMovePos(12);
        this.gameBoard[18].setMovePos(38);
        this.gameBoard[20].setMovePos(74);
        this.gameBoard[22].setMovePos(2);
        this.gameBoard[24].setMovePos(36);
        this.gameBoard[28].setMovePos(6);
        this.gameBoard[30].setMovePos(10);
        this.gameBoard[32].setMovePos(56);
        this.gameBoard[40].setMovePos(60);
        this.gameBoard[44].setMovePos(26);
        this.gameBoard[48].setMovePos(54);
        this.gameBoard[58].setMovePos(42);
        this.gameBoard[66].setMovePos(14);
        this.gameBoard[68].setMovePos(52);
        this.gameBoard[70].setMovePos(88);
        this.gameBoard[72].setMovePos(50);
        this.gameBoard[76].setMovePos(86);
        this.gameBoard[80].setMovePos(100);
        this.gameBoard[84].setMovePos(62);
        this.gameBoard[90].setMovePos(92);
        this.gameBoard[94].setMovePos(64);
        this.gameBoard[96].setMovePos(82);
        this.gameBoard[98].setMovePos(78);
    }

    /**
     * ������ �����ϴ� �޼ҵ�
     */
    public void play(){
        while(true){
            // �÷��̾���� ��ȯ�Ͽ� �÷����Ѵ�.
            for(int playerIndex = 0; playerIndex < this.playerCount; playerIndex++){

                // �÷��̾��� ��ŷ�� �⺻��(0)�� �ƴϸ� ������� ������ ���̹Ƿ�
                // continue �� �Ѵ�.
                if(this.players[playerIndex].getRanking() != 0)
                    continue;

                // players[playerIndex]�� UserPlayer �ν��Ͻ��� ��
                if(this.players[playerIndex] instanceof UserPlayer){

                    // �÷��̾ ����̹Ƿ� ��ɾ� �ý����� �����Ѵ�.
                    if(this.command.commandSystem(this.players, playerIndex, this.gameBoard))
                        return;
                }

                // players[playerIndex]�� UserPlayer �ν��Ͻ��� �ƴ� ��
                else
                    this.players[playerIndex].play(this.gameBoard);

                // �÷��̾��� ��ġ�� 100(�����)�� �Ǹ�
                if(this.players[playerIndex].getPos() >= 100){
                    System.out.println(this.players[playerIndex].getPlayerID() + "�÷��̾���� ������� �����߽��ϴ�..");

                    // �÷��̾� ��ŷ�� 1 ���� ��Ų��
                    // �÷��̾�� ��ŷ�� �����Ѵ�.
                    this.rankding += 1;
                    this.players[playerIndex].setRanking(this.rankding);
                }

                // �÷��̾� ��ŷ�� �ο����� �������ٸ�
                // ������ �����Ѵ�.
                if(this.rankding == this.playerCount)
                    return;
            }
        }
    }

    /**
     * �÷��̾��� ������ ����ϴ� �޼ҵ�
     */
    public void printRanking(){
        // key:     ����
        // value:   �÷��̾� �ε���
        HashMap<Integer, Integer> playerRanking = new HashMap<>();

        // playerRanking(Map) �� ���� �����Ѵ�.
        for(int playerIndex = 0; playerIndex < this.playerCount; playerIndex++)
            playerRanking.put(this.players[playerIndex].getRanking(), this.players[playerIndex].getPlayerID());

        // playerRanking(Map)���� ����(Ű)�� ���� �����ͼ�
        // �������� ����Ѵ�.
        for(int ranking = 1; ranking <= this.playerCount; ranking++){
            int playerIndex = playerRanking.get(ranking);
            System.out.println(ranking + "��: " + this.players[playerIndex].getPlayerID() + "�÷��̾�");
        }
    }
}


