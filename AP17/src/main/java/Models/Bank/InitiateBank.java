package Models.Bank;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class InitiateBank {

    public static HashMap<String, String> tokens;
    public static ArrayList<String> expiredTokens;

    public static void main(String[] args) throws IOException {
        tokens = new HashMap<>();
        expiredTokens = new ArrayList<>();
        ServerSocket serverSocket = new ServerSocket(2020);
        while (true) {
            Socket socket = serverSocket.accept();
            new BankClientHandler(socket).start();
        }
    }

    public static HashMap<String, String> getTokens() {
        return tokens;
    }

    public static void setTokens(HashMap<String, String> tokens) {
        InitiateBank.tokens = tokens;
    }
}