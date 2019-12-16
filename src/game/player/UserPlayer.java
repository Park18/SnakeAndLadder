package game.player;

import game.GameBoard;

import java.util.Scanner;

public class UserPlayer extends BasePlayer{
    /*------------------------------------------------------------
     * ����
     *------------------------------------------------------------*/
    Scanner scanner = new Scanner(System.in);   // Console �ڿ�

    /*------------------------------------------------------------
     * �޼ҵ�
     *------------------------------------------------------------*/

    /**
     * UserPlayer ������
     * @param playerID �÷��̾� ���̵�
     */
    public UserPlayer(int playerID){
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
        while(true){
            System.out.println(getPlayerID() + "�� �÷��̾�� �ֻ����� �����ðڽ��ϱ�?");
            System.out.print("[Y/N] >> ");

            char answer = scanner.next().charAt(0);
            if(answer == 'y' || answer == 'Y'){
                int result = roll();
                System.out.println(result + "�� ���Խ��ϴ�.\n");
                movePos(gameBoard, result);

                // ������ ��������Ƿ� ��� �÷����Ѵ�.
                return false;
            }

            else if(answer == 'n' || answer == 'N'){
                System.out.println("������ ������ ��ٸ��ڽ��ϴ�.\n");

                // ������ ��������Ƿ� ���� ����Ѵ�.
                return true;
            }

            else
                System.out.println("�߸��� �Է��� ���Դϴ�. �ٽ� �Է����ּ���.\n");
        }
    }
}
