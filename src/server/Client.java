package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public void test() throws IOException {
        Socket socket = new Socket("localhost", 5555);

        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        printWriter.println("hello");
        printWriter.flush();
    }
}
