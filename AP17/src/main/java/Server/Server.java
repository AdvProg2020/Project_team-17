package Server;

import Models.Bank.BankClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    public static BankClient.Client client;
    public static ArrayList<String> listOfTokens = new ArrayList<>();
    static final int PORT = 1989;

    public static void main(String args[]) {
        ServerSocket serverSocket = null;
        Socket socket;
//        BankClient bankClient = new BankClient();
//        client = new BankClient.Client();
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("socket created");
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                socket = serverSocket.accept();
                System.out.println("connection settled");
                ClientHandler clientHandler = new ClientHandler(socket);
                System.out.println("thread is going to start");
                clientHandler.start();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}