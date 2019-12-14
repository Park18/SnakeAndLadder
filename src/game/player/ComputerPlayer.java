package game.player;

import game.GameBoard;

public class ComputerPlayer extends BasePlayer{

    /**
     * ComputerPlayer ������
     * @param playerID �÷��̾� ���̵�
     */
    public ComputerPlayer(int playerID){
        super(playerID);
    }

    /**
     * �÷��̾ ������ �÷����ϴ� �޼ҵ�
     * BasePlayer.play() �߻�޼��� ����
     * @param gameBoard ���Ӻ���
     * @return true: ���� ��� / false: ��� ����
     */
    @Override
    public boolean play(GameBoard[] gameBoard) {
        System.out.println("<" + getPlayerID() + "�÷��̾���� �����Դϴ�.>");
        System.out.println(getPlayerID() + "�÷��̾���� �ֻ����� �����ϴ�.");

        int result = roll();
        System.out.println(result + "�� ���Խ��ϴ�.\n");
        movePos(gameBoard, result);

        // ��ǻ�ʹ� ������ ��� �����ϱ� ������ false �� ��ȯ�Ѵ�.
        return false;
    }
}
