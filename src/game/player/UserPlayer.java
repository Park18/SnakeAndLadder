package game.player;

import game.GameBoard;

import java.util.Scanner;

public class UserPlayer extends BasePlayer{
    /*------------------------------------------------------------
     * 변수
     *------------------------------------------------------------*/
    Scanner scanner = new Scanner(System.in);   // Console 자원

    /*------------------------------------------------------------
     * 메소드
     *------------------------------------------------------------*/

    /**
     * UserPlayer 생성자
     * @param playerID 플레이어 아이디
     */
    public UserPlayer(int playerID){
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
        while(true){
            System.out.println(getPlayerID() + "번 플레이어님 주사위를 굴리시겠습니까?");
            System.out.print("[Y/N] >> ");

            char answer = scanner.next().charAt(0);
            if(answer == 'y' || answer == 'Y'){
                int result = roll();
                System.out.println(result + "이 나왔습니다.\n");
                movePos(gameBoard, result);

                // 긍정을 대답했으므로 계속 플레이한다.
                return false;
            }

            else if(answer == 'n' || answer == 'N'){
                System.out.println("돌리실 때까지 기다리겠습니다.\n");

                // 부정을 대답했으므로 실행 취소한다.
                return true;
            }

            else
                System.out.println("잘못된 입력을 값입니다. 다시 입력해주세요.\n");
        }
    }
}
