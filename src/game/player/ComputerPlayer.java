package game.player;

import game.GameBoard;

public class ComputerPlayer extends BasePlayer{

    /**
     * ComputerPlayer 생성자
     * @param playerID 플레이어 아이디
     */
    public ComputerPlayer(int playerID){
        super(playerID);
    }

    /**
     * 플레이어가 게임을 플레이하는 메소드
     * BasePlayer.play() 추상메서드 구현
     * @param gameBoard 게임보드
     * @return true: 실행 취소 / false: 계속 실행
     */
    @Override
    public boolean play(GameBoard[] gameBoard) {
        System.out.println("<" + getPlayerID() + "플레이어님의 차례입니다.>");
        System.out.println(getPlayerID() + "플레이어님이 주사위를 굴립니다.");

        int result = roll();
        System.out.println(result + "이 나왔습니다.\n");
        movePos(gameBoard, result);

        // 컴퓨터는 무조건 계속 실행하기 때문에 false 만 반환한다.
        return false;
    }
}
