package game;

import game.player.BasePlayer;

import java.util.Scanner;

public class Command {
    private Scanner scanner = new Scanner(System.in);

    /**
     * 명령어 시스템 메소드
     * @param players 플레이어 배열
     * @param nowPlayerIndex 현재 시스템을 사용하는 players 의 인덱스
     * @param gameBoard 게임보드
     * @return true: 종료 / false: 미종료
     */
    public boolean commandSystem(final BasePlayer players[], int nowPlayerIndex, final GameBoard[] gameBoard){
        String command; // 명령어 저장 변수
        String action;  // 명령어의 action 저장 변수

        System.out.println("<" + players[nowPlayerIndex].getPlayerID() + "번 플레이어님의 차례입니다.>");
        while(true){
            System.out.print("[명령어] >> ");
            command = this.scanner.nextLine();

            action = commandAction(command);
            if(commandCheck(command, action)){

                // action 이 roll 일 때
                if(action.equals("roll")){

                    // 플레이어가 주사위를 굴린다. (반환값 true: 실행 취소 / false: 계속 실행)
                    if(playerRoll(players[nowPlayerIndex], gameBoard))
                        continue;

                    // 플레이어의 위치가 100 이상이면 승리
                    if(players[nowPlayerIndex].getPos() >= 100){
                        System.out.println(players[nowPlayerIndex].getPlayerID() + "플레이어님이 승리했습니다.");
                        return true;
                    }

                    return false;
                }

                // action 이 pos 일 때
                else if(action.equals("pos"))
                    playerPos(players[nowPlayerIndex]);

                // action 이 checkInfo 일 때
                else if(action.equals("checkInfo")){
                    int blankIndex = command.indexOf(" ");
                    int lastIndex = command.length();

                    String player = command.substring(blankIndex + 1, lastIndex);

                    try{
                        playerInfo(players[Integer.parseInt(player)], gameBoard);

                    } catch (NumberFormatException numberFormatException){
                        System.out.println("잘못된 명령어 입니다.");
                    }
                }

                //action 이 exit 일 떼
                else if(action.equals("exit")){
                    System.out.println("<" + players[nowPlayerIndex].getPlayerID() + "플레이어님이 종료합니다.>");
                    return true;
                }

                else
                    System.out.println("잘못된 명령어 입니다. 다시 입력해주세요.");
            }
        }
    }

    /**
     * 옳바른 명령어인지 확인하는 메소드
     * @param command 명령어
     * @param action 명령어 action
     * @return true: 옳바른 명령어 / false: 옳바르지 않은 명령어
     */
    public boolean commandCheck(String command, String action){
        int blankIndex = command.indexOf(" ");

        // 명령어 분기점이 있을 때
        // String.contains() 를 쓰지 않은 이유는
        // String.subString 에 index 값이 필요하기 때문에
        // 굳이 연산을 두 번할 필요가 없다고 판단했기 때문
        if(blankIndex != -1){

            // 명령어에서 분기점이 있을 때
            // action 이 roll 이거나 pos 이면 잘못된 명령어이다.
            if(action.equals("roll") || action.equals("pos") || action.equals("exit"))
                return false;

            // 분기점을 가지는 action 에서 또다른 분기점이 있으면
            // 잘못된 명령엉이다.
            else if(action.equals("checkInfo")){
                String tmpCommand = command.substring(blankIndex + 1, command.length());
                blankIndex = tmpCommand.indexOf(" ");

                // 분기점이 두 개라면 잘못된 명령어이다.
                if(blankIndex != -1)
                    return false;

                return true;
            }
        }

        // 명령어 분기점이 없을 때
        // 명령어와 action 이 같아야 한다.
        else{
            if(command.equals(action)){

                // action 이 checkInfo 이면 잘못된 명령어 이다.
                if(action.equals("checkInfo"))
                    return false;

                return true;
            }
        }

        // 위 상황이 아니면 잘못된 명령어이다.
        return false;
    }

    /**
     * 명령어의 행동을 반환하는 메소드
     * @param command 명령어
     * @return 명령어의 행동
     */
    public String commandAction(String command){
        int blankIndex = command.indexOf(" ");

        // 명령어 분기점이 있을 때
        // String.contains() 를 쓰지 않은 이유는
        // String.subString 에 index 값이 필요하기 때문에
        // 굳이 연산을 두 번할 필요가 없다고 판단했기 때문
        if(blankIndex != -1)
            return command.substring(0, blankIndex);

        else
            return command;
    }

    /**
     * 명령어: roll
     * 플레이어(자신)가 주사위를 던진다.
     * @param player 플레이어
     * @return true: 실행 취소 / false: 계속 실행
     */
    public boolean playerRoll(BasePlayer player, GameBoard[] gameBoard){
        return player.play(gameBoard);
    }

    /**
     * 명령어: checkInfo player_name
     * 플레이어(모든)의 앞으로 6칸의 정보를 출력한다.
     * 정보: 사다리, 뱀, 일반
     * @param player 플레이어
     * @param gameBoard 게임판
     */
    public void playerInfo(BasePlayer player, GameBoard[] gameBoard){
        int boardIndex = player.getPos() - 1;

        if(boardIndex > -1){
            System.out.println("[" + player.getPlayerID() + "]");
            System.out.println("-------------info-------------");
            for(int index = boardIndex; index < boardIndex + 6; index++){
                if(index > 99) continue;
                gameBoard[index].printInfo();
            }
            System.out.println("-------------info-------------");
        }

        else
            System.out.println(player.getPlayerID() + "번 플레이어님은 아직 플레이 안했습니다.");

        System.out.println();
    }

    /**
     * 명령어: pos
     * 플레이어(자신)의 위치를 출력하는 메소드
     * @param player 플레이어
     */
    public void playerPos(BasePlayer player){
        System.out.println("[pos] : " + player.getPos() + "\n");
    }

    /**
     * 테스트 메소드
     */
    public void test(){
        while(true){
            String command = this.scanner.nextLine();
            String action = commandAction(command);

            if(command.equals("exit")) return;

            System.out.println(commandCheck(command, action));
        }
    }
}
