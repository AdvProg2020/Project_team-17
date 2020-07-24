package Models.Bank;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Models.Bank.BankServer.*;

public class BankClientHandler extends Thread {
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    boolean exit;

    public BankClientHandler(Socket socket) {
        this.socket = socket;
        try {
            dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            exit = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            functionHandle();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void functionHandle() throws IOException {
        while (true) {
            String input = dataInputStream.readUTF();
            String[] splitInputs = input.split("\\s");
            String string = splitInputs[0];
            if (string.equals("create_account")) {
                createAccount(splitInputs);
            } else if (string.equals("get_token")) {
                getToken(splitInputs);
            } else if (string.equals("create_receipt")) {
                createReceipt(input);
            } else if (string.equals("get_transactions")) {
                getTransactions(splitInputs);
            } else if (string.equals("pay")) {
                pay(splitInputs);
            } else if (string.equals("get_balance")) {
                getBalance(splitInputs);
            } else if (string.equals("exit")) {
                socket.close();
                exit = true;
            } else {
                dataOutputStream.writeUTF("invalid input");
                dataOutputStream.flush();
            }
            if (exit) {
                break;
            }
        }
    }


    private void createAccount(String[] splitInputs) {
        if (splitInputs[4].equals(splitInputs[5])) {
            if (!(BankAccount.isThereAccountWithThisUsername(splitInputs[3]))) {
                BankAccount bankAccount = new BankAccount(splitInputs[1], splitInputs[2], splitInputs[3], splitInputs[4]);
                try {
                    dataOutputStream.writeUTF(String.valueOf(bankAccount.getAccountId()));
                    dataOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    dataOutputStream.writeUTF("Username is not available");
                    dataOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                dataOutputStream.writeUTF("Passwords do not match");
                dataOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void getBalance(String[] splitInputs) {
        if (!expiredTokens.contains(splitInputs[1])) {
            if (tokens.containsKey(splitInputs[1])) {
                BankAccount bankAccount = BankAccount.getBankAccountWithUsername(tokens.get(splitInputs[1]));
                try {
                    dataOutputStream.writeUTF(String.valueOf(bankAccount.getBalance()));
                    dataOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    dataOutputStream.writeUTF("Token is invalid");
                    dataOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                dataOutputStream.writeUTF("Token expired");
                dataOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void pay(String[] splitInputs) {
        int receiptId = Integer.parseInt(splitInputs[1]);
        Receipt receipt = Receipt.getReceiptById(receiptId);
        if (receipt == null) {
            try {
                dataOutputStream.writeUTF("Invalid receipt id");
                dataOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (receipt.isPaid()) {
            try {
                dataOutputStream.writeUTF("Receipt is paid before");
                dataOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (receipt.getType().equals("move")) {
            if (BankAccount.getAccountWithId(receipt.getSourceId()) == null || BankAccount.getAccountWithId(receipt.getDestinationId()) == null) {
                try {
                    dataOutputStream.writeUTF("Invalid account id");
                    dataOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (BankAccount.getAccountWithId(receipt.getSourceId()).getBalance() < receipt.getMoney()) {
                try {
                    dataOutputStream.writeUTF("Source account does not have enough money");
                    dataOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                BankAccount source = BankAccount.getAccountWithId(receipt.getSourceId());
                BankAccount destination = BankAccount.getAccountWithId(receipt.getDestinationId());
                source.setBalance(source.getBalance() - receipt.getMoney());
                destination.setBalance(destination.getBalance() + receipt.getMoney());
                receipt.setPaid(true);
                try {
                    dataOutputStream.writeUTF("Done successfully");
                    dataOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (receipt.getType().equals("deposit")) {
            if (BankAccount.getAccountWithId(receipt.getDestinationId()) == null) {
                try {
                    dataOutputStream.writeUTF("Invalid account id");
                    dataOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                BankAccount destination = BankAccount.getAccountWithId(receipt.getDestinationId());
                destination.setBalance(destination.getBalance() + receipt.getMoney());
                receipt.setPaid(true);
                try {
                    dataOutputStream.writeUTF("Done successfully");
                    dataOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (receipt.getType().equals("withdraw")) {
            if (BankAccount.getAccountWithId(receipt.getSourceId()) == null) {
                try {
                    dataOutputStream.writeUTF("Invalid account id");
                    dataOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (BankAccount.getAccountWithId(receipt.getSourceId()).getBalance() < receipt.getMoney()) {
                try {
                    dataOutputStream.writeUTF("Source account does not have enough money");
                    dataOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                BankAccount source = BankAccount.getAccountWithId(receipt.getSourceId());
                source.setBalance(source.getBalance() - receipt.getMoney());
                receipt.setPaid(true);
                try {
                    dataOutputStream.writeUTF("Done successfully");
                    dataOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void getTransactions(String[] splitInputs) {
        String token = splitInputs[1];
        String type = splitInputs[2];
        if (!expiredTokens.contains(token)) {
            if (tokens.containsKey(splitInputs[1])) {
                //TODO ASK KIAN
                Gson gson = new GsonBuilder().serializeNulls().create();
                BankAccount bankAccount = BankAccount.getBankAccountWithUsername(tokens.get(token));
                if (type.equals("+")) {
                    ArrayList<Receipt> receiptsResult = new ArrayList<>();
                    for (Receipt receipt : Receipt.getAllReceipts()) {
                        if (receipt.getDestinationId() == bankAccount.getAccountId()) {
                            receiptsResult.add(receipt);
                        }
                    }
                    String data = gson.toJson(receiptsResult);
                    try {
                        dataOutputStream.writeUTF(data);
                        dataOutputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (type.equals("-")) {
                    ArrayList<Receipt> receiptsResult = new ArrayList<>();
                    for (Receipt receipt : Receipt.getAllReceipts()) {
                        if (receipt.getDestinationId() == bankAccount.getAccountId()) {
                            receiptsResult.add(receipt);
                        }
                    }
                    String data = gson.toJson(receiptsResult);
                    try {
                        dataOutputStream.writeUTF(data);
                        dataOutputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else if (type.equals("*")) {
                    ArrayList<Receipt> receiptsResult = new ArrayList<>();
                    for (Receipt receipt : Receipt.getAllReceipts()) {
                        if (receipt.getSourceId() == bankAccount.getAccountId() ||
                                receipt.getDestinationId() == bankAccount.getAccountId()) {
                            receiptsResult.add(receipt);
                        }
                    }
                    String data = gson.toJson(receiptsResult);
                    try {
                        dataOutputStream.writeUTF(data);
                        dataOutputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    for (Receipt i : Receipt.getAllReceipts()) {
                        if (i.getId() == Integer.parseInt(splitInputs[2])) {
                            String data = gson.toJson(i);
                            try {
                                dataOutputStream.writeUTF(data);
                                dataOutputStream.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return;
                        }
                    }
                    try {
                        dataOutputStream.writeUTF("Token is invalid");
                        dataOutputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            try {
                dataOutputStream.writeUTF("Token expired");
                dataOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void createReceipt(String input) {
        String[] splitInputs = input.split("\\s");
        String check = checkInput(input);
        if (check.equals("ok")) {
            if (!expiredTokens.contains(splitInputs[1])) {
                if (tokens.containsKey(splitInputs[1])) {
                    String type = splitInputs[2];
                    double money = Double.parseDouble(splitInputs[3]);
                    int sourceId = Integer.parseInt(splitInputs[4]);
                    int destinationId = Integer.parseInt(splitInputs[5]);
                    String description = null;
                    if (splitInputs.length > 6) {
                        description = splitInputs[6];
                    }
                    if (type.equals("deposit") && sourceId != -1) {
                        try {
                            dataOutputStream.writeUTF("Source account id is invalid");
                            dataOutputStream.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (type.equals("withdraw") && destinationId != -1) {
                        try {
                            dataOutputStream.writeUTF("Destination account id is invalid");
                            dataOutputStream.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (type.equals("withdraw") && sourceId != BankAccount.getBankAccountWithUsername(tokens.get(splitInputs[1])).getAccountId()) {
                        try {
                            dataOutputStream.writeUTF("Token is invalid");
                            dataOutputStream.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (type.equals("move") && sourceId != BankAccount.getBankAccountWithUsername(tokens.get(splitInputs[1])).getAccountId()) {
                        try {
                            dataOutputStream.writeUTF("Token is invalid");
                            dataOutputStream.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (type.equals("move") && sourceId == destinationId) {
                        try {
                            dataOutputStream.writeUTF("Equal source and destination account");
                            dataOutputStream.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (type.equals("move") && (sourceId == -1 || destinationId == -1)) {
                        try {
                            dataOutputStream.writeUTF("Invalid account id");
                            dataOutputStream.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Receipt receipt = new Receipt(type, money, sourceId, destinationId, description);
                        try {
                            dataOutputStream.writeUTF(String.valueOf(receipt.getId()));
                            dataOutputStream.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    try {
                        dataOutputStream.writeUTF("Token is invalid");
                        dataOutputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                try {
                    dataOutputStream.writeUTF("Token expired");
                    dataOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String checkInput(String input) {
        String regex = "create_receipt (\\S+) (\\S+) (\\S+) (-?\\d+) (-?\\d+)( \\S+)?";
        String[] splitInputs = input.split("\\s");
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (!matcher.find()) {
            return "Invalid parameters passed";
        } else {
            try {
                double test = Double.parseDouble(splitInputs[3]);
            } catch (NumberFormatException e) {
                return "Invalid money";
            }
            if (splitInputs[2].equals("deposit") || splitInputs[2].equals("withdraw") || splitInputs[2].equals("move")) {
                return "ok";
            } else {
                return "Invalid receipt type";
            }
        }
    }

    private void getToken(String[] splitInputs) {
        if (BankAccount.isThereAccountWithThisUsername(splitInputs[1])) {
            BankAccount bankAccount = BankAccount.getBankAccountWithUsername(splitInputs[1]);
            if (splitInputs[2].equals(bankAccount.getPassword())) {
                if (tokens.containsValue(splitInputs[1])) {
                    try {
                        String token = null;
                        for (String i : tokens.keySet()) {
                            if (tokens.get(i).equals(splitInputs[1])) {
                                token = i;
                            }
                        }
                        dataOutputStream.writeUTF(token);
                        dataOutputStream.flush();
                        return;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                String token = generateToken();
                tokens.put(token, bankAccount.getUsername());
                class TokenTimer extends TimerTask {
                    private String token;

                    public TokenTimer(String token) {
                        this.token = token;
                    }

                    @Override
                    public void run() {
                        expiredTokens.add(token);
                        tokens.remove(token);
                    }
                }
                TimerTask timerTask = new TokenTimer(token);
                Timer timer = new Timer();
                timer.schedule(timerTask, 3600000);
                try {
                    dataOutputStream.writeUTF(token);
                    dataOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    dataOutputStream.writeUTF("Invalid username or password");
                    dataOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                dataOutputStream.writeUTF("Invalid username or password");
                dataOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String generateToken() {
        final String ALPHA_NUMERIC_STRING = "NAKAPPROJECT";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            stringBuilder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return stringBuilder.toString();
    }
}