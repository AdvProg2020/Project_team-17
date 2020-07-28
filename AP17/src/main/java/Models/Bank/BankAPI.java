package Models.Bank;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class BankAPI {

    public final int PORT = 17475;
    public final String IP = "2.tcp.ngrok.io";
    public final int SHOP_SERVER_PORT = 8000;

    private DataOutputStream outputStream;
    private DataInputStream inputStream;

    private String token;
    private static String response;

    public static String getResponse() {
        return response;
    }

    public static void setResponse(String response) {
        BankAPI.response = response;
    }

    public void connectToBankServer() throws IOException {
        try {
            Socket socket = new Socket(IP, PORT);
            outputStream = new DataOutputStream(socket.getOutputStream());
            inputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            throw new IOException("Exception while initiating connection:");
        }
    }

//    public void startListeningOnInput() {
//        new Thread(() -> {
//            while (true) {
//                try {
//                    shopOutputStream.writeUTF(inputStream.readUTF());
//                } catch (IOException e) {
//                    System.out.println("disconnected");
//                    System.exit(0);
//                }
//            }
//        }).start();
//    }

    public void sendMessage(String msg) throws IOException {
        try {
            outputStream.writeUTF(msg);
            String response = inputStream.readUTF();
            System.out.println(response);
            setResponse(response);
        } catch (IOException e) {
            throw new IOException("Exception while sending message:");
        }
    }

    public void run() {
        try {
            response = "";
            connectToBankServer();
//            startListeningOnInput();
//            while (true) {
//                String messageToSend = inputStream.readUTF();
//                sendMessage(messageToSend);
//            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
}