package Models.Bank;

import java.io.*;
import java.net.Socket;

public class BankClient {

    public static void main(String[] args) {
        new ClientImplementation().run();
    }

    public static class ClientImplementation {
        public final int PORT = 3989;
        public final String IP = "127.0.0.1";
        public final int SHOP_SERVER_PORT = 9000;

        private DataOutputStream outputStream;
        private DataInputStream inputStream;
        private DataOutputStream shopOutputStream;
        private DataInputStream shopInputStream;

        private String token;

        public void connectToBankServer() throws IOException {
            try {
                Socket socket = new Socket(IP, PORT);
                outputStream = new DataOutputStream(socket.getOutputStream());
                inputStream = new DataInputStream(socket.getInputStream());
            } catch (IOException e) {
                throw new IOException("Exception while initiating connection:");
            }
        }

        public void startListeningOnInput() {
            new Thread(() -> {
                while (true) {
                    try {
                        shopOutputStream.writeUTF(inputStream.readUTF());
                    } catch (IOException e) {
                        System.out.println("disconnected");
                        System.exit(0);
                    }
                }
            }).start();
        }

        public void sendMessage(String msg) throws IOException {
            try {
                outputStream.writeUTF(msg);
            } catch (IOException e) {
                throw new IOException("Exception while sending message:");
            }
        }

        public void run() {
            try {
                connectToBankServer();
                startListeningOnInput();
                while (true) {
                    String messageToSend = inputStream.readUTF();
                    sendMessage(messageToSend);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

        }
    }
}