package Models.Bank;

import java.io.IOException;

public class BankClient {

    public static void main(String[] args) throws IOException {
        BankAPI bankAPI = new BankAPI();
        bankAPI.run();
        System.out.println("Connection to Bank Initialized!");

        bankAPI.sendMessage("create_account Nona ghazizadeh ngh 1234 1234");
        String response = bankAPI.getResponse();
        System.out.println(response);
    }
}