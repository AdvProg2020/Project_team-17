package Client;

import java.io.*;
import java.net.Socket;

public class Client {

    public static Socket clientSocket;
    public static String token;
    public static DataInputStream dataInputStream;
    public static DataOutputStream dataOutputStream;
    public static ObjectOutputStream objectOutputStream;
    public static ObjectInputStream objectInputStream;


    public static void run() throws IOException {
        System.out.println("client is running!");
        clientSocket = new Socket("localhost", 1989);
        dataInputStream = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
        dataInputStream = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
        dataOutputStream = new DataOutputStream(new DataOutputStream(clientSocket.getOutputStream()));
        objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
        token = dataInputStream.readUTF();
        System.out.println("I/O streams initialized");
        System.out.println(token);
    }

    public static void sendMessage(String string) {
        sendObject(string);
//        string += token;
//        try {
//            dataOutputStream.writeUTF(string);
//            dataOutputStream.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static String receiveMessage() {
        String message = null;
        message = (String) receiveObject();
        return message;
//        try {
//            return dataInputStream.readUTF();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
    }

    public static void sendObject(Object sent) {

        try {
            System.out.println("Client Said: " + sent);
            objectOutputStream.writeObject(sent);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
//            objectOutputStream.writeObject(sent);
//            objectOutputStream.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static Object receiveObject() {
        Object object = null;
        try {
            object = objectInputStream.readObject();
            System.out.println("Server Said: " + object);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return object;

//        try {
//            return objectInputStream.readObject();
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return null;
    }
}