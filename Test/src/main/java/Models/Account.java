package Models;

import java.util.ArrayList;

public abstract class  Account {
    private static ArrayList<Account> allAccounts = new ArrayList<Account>();
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private ArrayList<DiscountCode> allDiscountCodes;


    public Account(String userName, String firstName, String lastName, String email, String phoneNumber, String password) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        allDiscountCodes = new ArrayList<DiscountCode>();
        allAccounts.add(this);
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

    public Account getAccountByUserName(String UserName) {
        for (Account account : allAccounts) {
            if (account.getUserName().equals(userName)) {
                return account;
            }
        }
        return null;
    }
    public void changeFirstName(String firstName){

    }
    public void changeLastName(String lastName){

    }
    public void changePassword(String password){

    }
    public void changeEmail(String email){

    }
    public void changePhoneNumber(String phoneNumber){

    }
    public boolean isUserNameAndPasswordCorrect(String userName , String password){
        if(getAccountByUserName(userName)!=null){
            if(getAccountByUserName(userName).getPassword().equals(password)){
                return true;
            }
            return false;
        }
        return false;
    }


}

