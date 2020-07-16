package Server;

import Server.ServerController.AccountsController.ManagerController;

import java.io.*;
import java.lang.Thread;
import java.net.Socket;

public class ClientHandler extends Thread {
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
            ;
        }
        String line;
        String generatedToken = generateToken();
        System.out.println("token generated");
        try {
            dataOutputStream.writeUTF(generatedToken);
            System.out.println(generatedToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                line = dataInputStream.readUTF();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void writeToClient(String dataToWrite) {
        try {
            dataOutputStream.writeUTF(dataToWrite);
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readDataFromClient() {
        String readData;
        try {
            readData = dataInputStream.readUTF();
            return readData;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object readObjectFromClient() {
        Object object;
        try {
            object = objectInputStream.readObject();
            return object;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeObjectToClient(Object object) {
        try {
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public static void handleFunction(String command) throws Exception {
        if (command.equals("Show Manager Info")) {
            ManagerController.showManagerInfo();
        } else if (command.equals("Edit Manager Info")) {
            ManagerController.editManagerInfo();
        }
        //TODO other commands
    }

}