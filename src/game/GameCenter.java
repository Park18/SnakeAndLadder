package game;

import game.player.BasePlayer;
import game.player.ComputerPlayer;
import game.player.UserPlayer;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * 게임의 중앙처리 메서드
 * 소켓 미지원 중앙처리
 */
public class GameCenter {
    /*------------------------------------------------------------
     * 변수
     *------------------------------------------------------------*/
    private static int rankding = 0;                    // 플레이어 랭킹

    private BasePlayer[] players;                       // 플레이어 저장 배열
    private int playerCount;                            // 플레이어 인원수
    private GameBoard[] gameBoard = new GameBoard[100]; // 게임 보드판 (크기 100)
    private Scanner scanner = new Scanner(System.in);   // Console 자원
    private Command command = new Command();            // 명령어 객체

    /*------------------------------------------------------------
     * 메소드
     *------------------------------------------------------------*/

    /**
     * 생성자
     * player, gameBoard 초기화
     */
    public GameCenter(){
        playerInit();
        gameBoardInit();
    }

    /**
     * 플레이어 초기화 메소드
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
     * 맨 처음 게임 실행시 인원을 정하는 메소드
     * @return 게임 인원
     */
    public int playerCountInit(){
        System.out.println("함께 게임할 인원을 입력해주세요.(최소 2명 / 최대 6명)");

        while(true){
            System.out.print("[인원] >> ");
            int playerCount = 0;

            if(scanner.hasNextInt())
                playerCount = scanner.nextInt();

            else{
                System.out.println("숫자가 아닙니다. 다시 입력해주세요.");
                scanner.nextLine();
                continue;
            }

            if(playerCount < 2 || playerCount > 6)
                System.out.println("잘못된 범위를 입력했습니다. 다시 입력해 주세요.");

            else
                return playerCount;
        }
    }

    /**
     * 게임판을 초기화하는 메소드
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
     * 게임을 시작하는 메소드
     */
    public void play(){
        while(true){
            // 플레이어들을 순환하여 플레이한다.
            for(int playerIndex = 0; playerIndex < this.playerCount; playerIndex++){

                // 플레이어의 랭킹이 기본값(0)이 아니면 결승점에 도착한 것이므로
                // continue 를 한다.
                if(this.players[playerIndex].getRanking() != 0)
                    continue;

                // players[playerIndex]가 UserPlayer 인스턴스일 때
                if(this.players[playerIndex] instanceof UserPlayer){

                    // 플레이어가 사람이므로 명령어 시스템을 실행한다.
                    if(this.command.commandSystem(this.players, playerIndex, this.gameBoard))
                        return;
                }

                // players[playerIndex]가 UserPlayer 인스턴스가 아닐 때
                else
                    this.players[playerIndex].play(this.gameBoard);

                // 플레이어의 위치가 100(결승점)이 되면
                if(this.players[playerIndex].getPos() >= 100){
                    System.out.println(this.players[playerIndex].getPlayerID() + "플레이어님이 결승점에 도착했습니다..");

                    // 플레이어 랭킹을 1 증가 시킨후
                    // 플레이어에게 랭킹을 설정한다.
                    this.rankding += 1;
                    this.players[playerIndex].setRanking(this.rankding);
                }

                // 플레이어 랭킹이 인원수와 같아진다면
                // 게임을 종료한다.
                if(this.rankding == this.playerCount)
                    return;
            }
        }
    }

    /**
     * 플레이어의 순위를 출력하는 메소드
     */
    public void printRanking(){
        // key:     순위
        // value:   플레이어 인덱스
        HashMap<Integer, Integer> playerRanking = new HashMap<>();

        // playerRanking(Map) 에 값을 설정한다.
        for(int playerIndex = 0; playerIndex < this.playerCount; playerIndex++)
            playerRanking.put(this.players[playerIndex].getRanking(), this.players[playerIndex].getPlayerID());

        // playerRanking(Map)에서 순위(키)의 값을 가져와서
        // 순위별로 출력한다.
        for(int ranking = 1; ranking <= this.playerCount; ranking++){
            int playerIndex = playerRanking.get(ranking);
            System.out.println(ranking + "등: " + this.players[playerIndex].getPlayerID() + "플레이어");
        }
    }
}


