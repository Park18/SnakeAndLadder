package game.player;

import game.GameBoard;

import java.util.Random;

/**
 * BasePlayer
 * Player�� �⺻ Ŭ����
 */
public abstract class BasePlayer {
    /*------------------------------------------------------------
     * ����
     *------------------------------------------------------------*/
    protected int playerID;                 // �÷��̾� ���̵�
    protected int pos;                      // �÷��̾� ��ġ
    protected Random dice = new Random();   // ���� ������

    /*------------------------------------------------------------
     * �޼ҵ�
     *------------------------------------------------------------*/

    BasePlayer(int playerID){
        setPlayerID(playerID);
        setPos(0);
    }

    /**
     * playerId Set �޼���
     * @param playerID �÷��̾� ���̵�
     */
    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    /**
     * pos Set �޼ҵ�
     * @param pos �÷��̾��� ��ġ
     */
    public void setPos(int pos) {
        this.pos = pos;
    }

    /**
     * playerId get �޼ҵ�
     * @return �÷��̾� ���̵�
     */
    public int getPlayerID() {
        return playerID;
    }

    /**
     * pos Get �޼ҵ�
     * @return �÷��̾��� ���� ��ġ
     */
    public int getPos() {
        return pos;
    }

    /**
     * �ֻ����� ������ �޼ҵ�
     * @return �ֻ��� ��(1 ~ 6)�� ���� ��ȯ�Ѵ�.
     */
    public int roll(){
        return dice.nextInt(6) + 1;
    }

    /**
     * �÷��̾��� ��ġ�� �̵��ϴ� �޼ҵ�
     * @param gameBoard ���Ӻ���
     * @param result �ֻ��� ��� ��
     */
    public void movePos(GameBoard[] gameBoard, int result){
        this.pos += result;

        if(this.pos > 100)
            this.pos = 100;

        int index = this.pos - 1;

        if(gameBoard[index].isMove()){
            if(gameBoard[index].isUp()){
                System.out.println("��ٸ��� �߻��߽��ϴ�." + gameBoard[index].getMovePos() + "���� �̵��մϴ�.\n");
                this.pos = gameBoard[index].getMovePos();
            }

            else{
                System.out.println("���� �߻��߽��ϴ�." + gameBoard[index].getMovePos() + "���� �̵��մϴ�.\n");
                this.pos = gameBoard[index].getMovePos();
            }
        }
    }

    /**
     * �÷��̾ ������ �÷����ϴ� �޼ҵ�
     * BasePlayer.play() �߻�޼��� ����
     * @param gameBoard
     * @return true: ���� ��� / false: ��� ����
     */
    public abstract boolean play(GameBoard[] gameBoard);
}
