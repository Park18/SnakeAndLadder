package game;

import game.player.BasePlayer;

import java.util.Scanner;

public class Command {
    private Scanner scanner = new Scanner(System.in);

    /**
     * ��ɾ� �ý��� �޼ҵ�
     * @param players �÷��̾� �迭
     * @param nowPlayerIndex ���� �ý����� ����ϴ� players �� �ε���
     * @param gameBoard ���Ӻ���
     * @return true: ���� / false: ������
     */
    public boolean commandSystem(final BasePlayer players[], int nowPlayerIndex, final GameBoard[] gameBoard){
        String command; // ��ɾ� ���� ����
        String action;  // ��ɾ��� action ���� ����

        System.out.println("<" + players[nowPlayerIndex].getPlayerID() + "�� �÷��̾���� �����Դϴ�.>");
        while(true){
            System.out.print("[��ɾ�] >> ");
            command = this.scanner.nextLine();

            action = commandAction(command);
            if(commandCheck(command, action)){

                // action �� roll �� ��
                if(action.equals("roll")){

                    // �÷��̾ �ֻ����� ������. (��ȯ�� true: ���� ��� / false: ��� ����)
                    if(playerRoll(players[nowPlayerIndex], gameBoard))
                        continue;

                    // �÷��̾��� ��ġ�� 100 �̻��̸� �¸�
                    if(players[nowPlayerIndex].getPos() >= 100){
                        System.out.println(players[nowPlayerIndex].getPlayerID() + "�÷��̾���� �¸��߽��ϴ�.");
                        return true;
                    }

                    return false;
                }

                // action �� pos �� ��
                else if(action.equals("pos"))
                    playerPos(players[nowPlayerIndex]);

                // action �� checkInfo �� ��
                else if(action.equals("checkInfo")){
                    int blankIndex = command.indexOf(" ");
                    int lastIndex = command.length();

                    String player = command.substring(blankIndex + 1, lastIndex);

                    try{
                        playerInfo(players[Integer.parseInt(player)], gameBoard);

                    } catch (NumberFormatException numberFormatException){
                        System.out.println("�߸��� ��ɾ� �Դϴ�.");
                    }
                }

                //action �� exit �� ��
                else if(action.equals("exit")){
                    System.out.println("<" + players[nowPlayerIndex].getPlayerID() + "�÷��̾���� �����մϴ�.>");
                    return true;
                }

                else
                    System.out.println("�߸��� ��ɾ� �Դϴ�. �ٽ� �Է����ּ���.");
            }
        }
    }

    /**
     * �ǹٸ� ��ɾ����� Ȯ���ϴ� �޼ҵ�
     * @param command ��ɾ�
     * @param action ��ɾ� action
     * @return true: �ǹٸ� ��ɾ� / false: �ǹٸ��� ���� ��ɾ�
     */
    public boolean commandCheck(String command, String action){
        int blankIndex = command.indexOf(" ");

        // ��ɾ� �б����� ���� ��
        // String.contains() �� ���� ���� ������
        // String.subString �� index ���� �ʿ��ϱ� ������
        // ���� ������ �� ���� �ʿ䰡 ���ٰ� �Ǵ��߱� ����
        if(blankIndex != -1){

            // ��ɾ�� �б����� ���� ��
            // action �� roll �̰ų� pos �̸� �߸��� ��ɾ��̴�.
            if(action.equals("roll") || action.equals("pos") || action.equals("exit"))
                return false;

            // �б����� ������ action ���� �Ǵٸ� �б����� ������
            // �߸��� ��ɾ��̴�.
            else if(action.equals("checkInfo")){
                String tmpCommand = command.substring(blankIndex + 1, command.length());
                blankIndex = tmpCommand.indexOf(" ");

                // �б����� �� ����� �߸��� ��ɾ��̴�.
                if(blankIndex != -1)
                    return false;

                return true;
            }
        }

        // ��ɾ� �б����� ���� ��
        // ��ɾ�� action �� ���ƾ� �Ѵ�.
        else{
            if(command.equals(action)){

                // action �� checkInfo �̸� �߸��� ��ɾ� �̴�.
                if(action.equals("checkInfo"))
                    return false;

                return true;
            }
        }

        // �� ��Ȳ�� �ƴϸ� �߸��� ��ɾ��̴�.
        return false;
    }

    /**
     * ��ɾ��� �ൿ�� ��ȯ�ϴ� �޼ҵ�
     * @param command ��ɾ�
     * @return ��ɾ��� �ൿ
     */
    public String commandAction(String command){
        int blankIndex = command.indexOf(" ");

        // ��ɾ� �б����� ���� ��
        // String.contains() �� ���� ���� ������
        // String.subString �� index ���� �ʿ��ϱ� ������
        // ���� ������ �� ���� �ʿ䰡 ���ٰ� �Ǵ��߱� ����
        if(blankIndex != -1)
            return command.substring(0, blankIndex);

        else
            return command;
    }

    /**
     * ��ɾ�: roll
     * �÷��̾�(�ڽ�)�� �ֻ����� ������.
     * @param player �÷��̾�
     * @return true: ���� ��� / false: ��� ����
     */
    public boolean playerRoll(BasePlayer player, GameBoard[] gameBoard){
        return player.play(gameBoard);
    }

    /**
     * ��ɾ�: checkInfo player_name
     * �÷��̾�(���)�� ������ 6ĭ�� ������ ����Ѵ�.
     * ����: ��ٸ�, ��, �Ϲ�
     * @param player �÷��̾�
     * @param gameBoard ������
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
            System.out.println(player.getPlayerID() + "�� �÷��̾���� ���� �÷��� ���߽��ϴ�.");

        System.out.println();
    }

    /**
     * ��ɾ�: pos
     * �÷��̾�(�ڽ�)�� ��ġ�� ����ϴ� �޼ҵ�
     * @param player �÷��̾�
     */
    public void playerPos(BasePlayer player){
        System.out.println("[pos] : " + player.getPos() + "\n");
    }

    /**
     * �׽�Ʈ �޼ҵ�
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
