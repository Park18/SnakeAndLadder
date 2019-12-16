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
     * �׽�Ʈ �޼ҵ�
     *
     * �׽�Ʈ ����
     * ������ �ִ� ���� Ŭ���̾�Ʈ�� ����� �ǰ� �����.
     * @throws IOException
     */
    public void test2() throws IOException {
        // ����
        Socket socket = new Socket("localhost", 5555);

        // Ŭ���̾�Ʈ -> ����
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());

        // ���� -> Ŭ���̾�Ʈ
        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        Scanner scanner = new Scanner(System.in);
        String message;

        while(true){
            System.out.print("[��ɾ�] >> ");
            message = scanner.nextLine();

            printWriter.println(message);
            printWriter.flush();

            if(message.equals("com -exit")){
                System.out.println("Ŭ���̾�Ʈ�� �����մϴ�.");
                return;
            }

            else if(message.equals("printInfo")){
                String info = bufferedReader.readLine();
                System.out.println(info);
            }
        }
    }
}
