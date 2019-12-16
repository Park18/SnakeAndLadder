package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public void test1() throws IOException {

    }

    /**
     * 테스트 메소드
     *
     * 테스트 내용
     * 서버에 있는 값이 클라이언트에 출력이 되게 만든다.
     * @throws IOException
     */
    public void test2() throws IOException {
        // 소켓
        Socket socket = new Socket("localhost", 5555);

        // 클라이언트 -> 서버
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());

        // 서버 -> 클라이언트
        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        Scanner scanner = new Scanner(System.in);
        String message;

        while(true){
            System.out.print("[명령어] >> ");
            message = scanner.nextLine();

            printWriter.println(message);
            printWriter.flush();

            if(message.equals("com -exit")){
                System.out.println("클라이언트를 종료합니다.");
                return;
            }

            else if(message.equals("printInfo")){
                String info = bufferedReader.readLine();
                System.out.println(info);
            }
        }
    }
}
