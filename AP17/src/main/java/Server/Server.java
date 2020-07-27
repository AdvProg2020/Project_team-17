package Server;

import Models.Bank.BankAPI;
import Models.Bank.BankClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    public static BankAPI bankAPI;

    public static void main(String args[]) throws IOException {
        ArrayList<String> listOfTokens = new ArrayList<>();

        ServerSocket serverSocket = null;
        Socket socket;

        bankAPI = new BankAPI();
        bankAPI.run();
        System.out.println("Connection to Bank Initialized!");

//        bankAPI.sendMessage("get_token a b");
//        String response = bankAPI.getResponse();
//        System.out.println(response);


        try {
            serverSocket = new ServerSocket(3040);
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

    public static BankAPI getBankAPI() {
        return bankAPI;
    }

    public static void setBankAPI(BankAPI bankAPI) {
        Server.bankAPI = bankAPI;
    }
}