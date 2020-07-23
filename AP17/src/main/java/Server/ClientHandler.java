package Server;

import Models.Accounts.Manager;
import Server.ServerController.AccountsController.ManagerController;
import Server.ServerController.HandleController;
import View.RegisterManagerMenu;

import java.io.*;
import java.lang.Thread;
import java.net.Socket;

public class ClientHandler extends Thread {
    private static Manager currentManager;
    protected Socket socket;
    private static final String ALPHA_NUMERIC_STRING = "NAKAPPROJECT";
    public static ObjectOutputStream objectOutputStream;
    public static ObjectInputStream objectInputStream;
    InputStream inputStream = null;
    DataInputStream dataInputStream = null;
    DataOutputStream dataOutputStream = null;

    public ClientHandler(Socket clientSocket) {
        this.socket = clientSocket;
    }

    public void run() {
        System.out.println("client thread is running");
        try {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("output stream initialized");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("initializing input stream");
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            System.out.println("input stream initialized");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("getting input");
            inputStream = socket.getInputStream();
            dataInputStream = new DataInputStream(new BufferedInputStream(inputStream));
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ignored) {

        }
        String generatedToken = generateToken();
        System.out.println("token generated");
        try {
            dataOutputStream.writeUTF(generatedToken);
            dataOutputStream.flush();
            System.out.println(generatedToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String command;
        while (true) {
            try {
                command = receiveMessage();
                //handleCommand(command);
                HandleController.handleFunction(command);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Manager getCurrentManager() {
        return currentManager;
    }

    public static String generateToken() {
        StringBuilder builder = new StringBuilder();
        int count = 25;
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    public static void handleCommand(String command) throws Exception {
    }


    public static void sendMessage(String dataToWrite){
        //System.out.println("Server Said: "+dataToWrite);
        sendObject(dataToWrite);
        //out.writeUTF(dataToWrite);
        //out.flush();
    }

    public static String receiveMessage() {
        String readData;
        readData = (String) receiveObject();
        return readData;
    }

    public static Object receiveObject() {
        Object object = null;
        try {
            object = objectInputStream.readObject();
            System.out.println("Client Said: "+object);
            return object;
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }

        return null;
    }

    public static void sendObject(Object object){
        try {
            System.out.println("Server Said: "+object);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
//
//    public void writeToClient(String dataToWrite) {
//        try {
//            dataOutputStream.writeUTF(dataToWrite);
//            dataOutputStream.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public String readDataFromClient() {
//        String readData;
//        try {
//            readData = dataInputStream.readUTF();
//            return readData;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public Object readObjectFromClient() {
//        Object object;
//        try {
//            object = objectInputStream.readObject();
//            return object;
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public void writeObjectToClient(Object object) {
//        try {
//            objectOutputStream.writeObject(object);
//            objectOutputStream.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }



}