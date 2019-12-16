package game.player;

import game.GameBoard;

import java.util.Random;

/**
 * BasePlayer
 * Player의 기본 클래스
 */
public abstract class BasePlayer {
    /*------------------------------------------------------------
     * 변수
     *------------------------------------------------------------*/
    protected int playerID;                 // 플레이어 아이디
    protected int pos;                      // 플레이어 위치
    protected int ranking;                  // 플레이어 순위
    protected Random dice = new Random();   // 랜덤 생성기

    /*------------------------------------------------------------
     * 메소드
     *------------------------------------------------------------*/

    /**
     * BasePlayer 생성자
     * @param playerID 플레이어 아이디
     */
    BasePlayer(int playerID){
        setPlayerID(playerID);
        setPos(0);
        setRanking(0);
    }

    /**
     * playerId Set 메서드
     * @param playerID 플레이어 아이디
     */
    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    /**
     * pos Set 메소드
     * @param pos 플레이어의 위치
     */
    public void setPos(int pos) {
        this.pos = pos;
    }

    /**
     * ranking Set 메소드
     * @param ranking 플레이어의 최종 순위
     */
    public void setRanking(int ranking){
        this.ranking = ranking;
    }

    /**
     * playerId get 메소드
     * @return 플레이어 아이디
     */
    public int getPlayerID() {
        return playerID;
    }

    /**
     * pos Get 메소드
     * @return 플레이어의 현재 위치
     */
    public int getPos() {
        return pos;
    }

    /**
     * ranking Get 메소드
     * @return
     */
    public int getRanking(){
        return ranking;
    }

    /**
     * 주사위를 굴리는 메소드
     * @return 주사위 값(1 ~ 6)의 값을 반환한다.
     */
    public int roll(){
        return dice.nextInt(6) + 1;
    }

    /**
     * 플레이어의 위치를 이동하는 메소드
     * @param gameBoard 게임보드
     * @param result 주사위 결과 값
     */
    public void movePos(GameBoard[] gameBoard, int result){
        this.pos += result;

        if(this.pos > 100)
            this.pos = 100;

        int index = this.pos - 1;

        if(gameBoard[index].isMove()){
            if(gameBoard[index].isUp()){
                System.out.println("사다리가 발생했습니다." + gameBoard[index].getMovePos() + "으로 이동합니다.\n");
                this.pos = gameBoard[index].getMovePos();
            }

            else{
                System.out.println("뱀이 발생했습니다." + gameBoard[index].getMovePos() + "으로 이동합니다.\n");
                this.pos = gameBoard[index].getMovePos();
            }
        }
    }

    /**
     * 플레이어가 게임을 플레이하는 메소드
     * BasePlayer.play() 추상메서드 구현
     * @param gameBoard
     * @return true: 실행 취소 / false: 계속 실행
     */
    public abstract boolean play(GameBoard[] gameBoard);
}
