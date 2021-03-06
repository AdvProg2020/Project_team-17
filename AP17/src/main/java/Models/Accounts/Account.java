package Models.Accounts;

import Models.Bank.BankAccount;
import Models.DiscountCode;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Account implements Serializable {
    protected String role;
    protected String userName;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String phoneNumber;
    protected String password;
    protected double credit;
    protected BankAccount bankAccount;
    protected String path;
    private ArrayList<DiscountCode> allDiscountCodes;

    public Account(String role, String userName, String firstName, String lastName, String email, String phoneNumber, String password, double credit, String path) {
        this.role = role;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.credit = credit;
        this.path = path;
        allDiscountCodes = new ArrayList<>();
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public double getCredit() {
        return credit;
    }

    public String getPath() {
        return path;
    }

    public String getRole() {
        return role;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public void changeFirstName(Account account, String firstName) {
        account.firstName = firstName;
    }

    public void changeLastName(Account account, String lastName) {
        account.lastName = lastName;
    }

    public void changePassword(Account account, String password) {
        account.password = password;
    }

    public void changeEmail(Account account, String email) {
        account.email = email;
    }

    public void changePhoneNumber(Account account, String phoneNumber) {
        account.phoneNumber = phoneNumber;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public abstract String toString();
}

