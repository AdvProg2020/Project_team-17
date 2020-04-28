package Models.Accounts;

import Models.DiscountCode;

import java.util.ArrayList;

public abstract class  Account {
    //private static ArrayList<Account> allAccounts = new ArrayList<Account>();
    protected String userName;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String phoneNumber;
    protected String password;
    protected int credit;
    private ArrayList<DiscountCode> allDiscountCodes;
    protected boolean isThisAccountLogged=false; //meghdare ino nemidonam to constructor bayad false bezram ya inja


    public Account(String userName, String firstName, String lastName, String email, String phoneNumber, String password,int credit) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.credit=credit;
        allDiscountCodes = new ArrayList<DiscountCode>();
        //allAccounts.add(this);
    }
    public void loginAccount(Account account){
        account.isThisAccountLogged=true;
    }
    public void logoutAccount(Account account){
        account.isThisAccountLogged=false;
    }
    public boolean getIsThisAccountLogged(Account account){
        return account.isThisAccountLogged;
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

    public int getCredit() {
        return credit;
    }

    /* public Account getAccountByUserName(String UserName) {
            for (Account account : allAccounts) {
                if (account.getUserName().equals(userName)) {
                    return account;
                }
            }
            return null;
        }*/
    public void changeFirstName(Account account,String firstName){
        account.firstName=firstName;
    }
    public void changeLastName(Account account,String lastName){
        account.lastName=lastName;
    }
    public void changePassword(Account account,String password){
        account.password=password;
    }
    public void changeEmail(Account account,String email){
        account.email=email;
    }
    public void changePhoneNumber(Account account,String phoneNumber){
        account.phoneNumber=phoneNumber;
    }
    /*public boolean isUserNameAndPasswordCorrect(String userName , String password){
        if(getAccountByUserName(userName)!=null){
            if(getAccountByUserName(userName).getPassword().equals(password)){
                return true;
            }
            return false;
        }
        return false;
    }*/
    @Override
    public abstract String toString();
}
