package Models.Bank;

import java.io.Serializable;
import java.util.ArrayList;

public class BankAccount implements Serializable {
    private int accountId;
    private double balance;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private static ArrayList<BankAccount> allBankAccounts = new ArrayList<>();

    public BankAccount(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.accountId = allBankAccounts.size() + 1000;
        this.balance = 10000;
        allBankAccounts.add(this);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public static ArrayList<BankAccount> getAllBankAccounts() {
        return allBankAccounts;
    }

    public static BankAccount getBankAccountWithUsername(String username) {
        for (BankAccount bankAccount : allBankAccounts) {
            if (bankAccount.getUsername().equals(username)) {
                return bankAccount;
            }
        }
        return null;
    }

    public static BankAccount getAccountWithId(int accountId) {
        for (BankAccount bankAccount : allBankAccounts) {
            if (bankAccount.getAccountId() == accountId) {
                return bankAccount;
            }
        }
        return null;
    }

    public static boolean isThereAccountWithThisUsername(String username) {
        for (BankAccount bankAccount : allBankAccounts) {
            if (bankAccount.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

}