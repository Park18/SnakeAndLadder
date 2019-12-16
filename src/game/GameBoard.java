package game;

/**
 * 게임판 클래스
 */
public class GameBoard {
    /*------------------------------------------------------------
     * 변수
     *------------------------------------------------------------*/
    private int pos;        // 칸의 위치를 저장하는 변수
    private int movePos;    // 칸이 이동할 위치를 저장하는 변수, 이동하지 않는다면 -1을 저장한다.

    /*------------------------------------------------------------
     * 메소드
     *------------------------------------------------------------*/

    /**
     * 생성자
     * 입력받은 pos로 설정, movePos는 -1로 설정
     * @param pos 칸의 위치
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
     * 해당 게임판이 이벤트가 있는 칸인지 확인하는 메소드
     * @return 만약 이동하는 칸이면 true를 반환, 아니면 false를 반환
     */
    public boolean isMove(){
        if(getMovePos() != -1)
            return true;

        return false;
    }

    /**
     * 해당 게임판이 어떤 이벤트인지 확인하는 메소드
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
     * 게임판의 속성을 출력해주는 메서드
     */
    public void printInfo(){
        System.out.print("[" + getPos() + "] : ");

        // 움직이는 속성일 때
        if(isMove()){

            // 위로 움직이는 속성일 때
            if(isUp())
                System.out.println("사다리");

            // 아래로 움직이는 속성일 때
            else
                System.out.println("뱀");
        }

        // 움직이는 속성이 아닐 때때
        else
           System.out.println("일반");
    }
}
