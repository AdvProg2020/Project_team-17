package Models.Bank;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class BankServer {

    public static HashMap<String, String> tokens;
    public static ArrayList<String> expiredTokens;

    public static void main(String[] args) throws IOException {
        tokens = new HashMap<>();
        expiredTokens = new ArrayList<>();
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("socket created");
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("connection settled");
            BankClientHandler bankClientHandler = new BankClientHandler(socket);
            System.out.println("thread is going to start");
            bankClientHandler.start();
        }
    }
}