package game;

/**
 * ������ Ŭ����
 */
public class GameBoard {
    private int pos;        // ĭ�� ��ġ�� �����ϴ� ����
    private int movePos;    // ĭ�� �̵��� ��ġ�� �����ϴ� ����, �̵����� �ʴ´ٸ� -1�� �����Ѵ�.

    /**
     * ������
     * �Է¹��� pos�� ����, movePos�� -1�� ����
     * @param pos ĭ�� ��ġ
     */
    public GameBoard(int pos){
        setPos(pos);
        setMovePos(-1);
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
    public void setMovePos(int movePos) {
        this.movePos = movePos;
    }
    public int getPos() {
        return pos;
    }
    public int getMovePos() {
        return movePos;
    }

    /**
     * �ش� �������� �̺�Ʈ�� �ִ� ĭ���� Ȯ���ϴ� �޼ҵ�
     * @return ���� �̵��ϴ� ĭ�̸� true�� ��ȯ, �ƴϸ� false�� ��ȯ
     */
    public boolean isMove(){
        if(getMovePos() != -1)
            return true;

        return false;
    }

    /**
     * �ش� �������� � �̺�Ʈ���� Ȯ���ϴ� �޼ҵ�
     * Up: movePos > pos
     * Down: movePos < pos
     * @return true: Up / false: Down
     */
    public boolean isUp() {
        if(getPos() < getMovePos())
            return true;

        return false;
    }

    /**
     * �������� �Ӽ��� ������ִ� �޼���
     */
    public void printInfo(){
        System.out.print("[" + getPos() + "] : ");

        if(isMove()){
            if(isUp())
                System.out.println("��ٸ�");
            else
                System.out.println("��");
        }

        else
            System.out.println("�Ϲ�");
    }
}
