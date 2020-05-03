package Models.Accounts;

import Controller.AccountsManager.SellerAbilitiesManager;
import Models.Product;
import Models.Logs.SellLog;

import java.util.ArrayList;

public class Seller extends Account {
    private SellerAbilitiesManager sellerAbilitiesManager;
    private String companyName;
    private ArrayList<Product> allProducts;
    private static ArrayList<Seller> allSellers= new ArrayList<Seller>();
    private SellLog sellLog;

    public Seller(String userName, String firstName, String lastName, String email
            , String phoneNumber, String password ,int credit,String companyName) {
        super(userName, firstName, lastName, email, phoneNumber, password,credit);
        this.companyName=companyName;
        allProducts = new ArrayList<>();
        allSellers.add(this);
    }

    public void setSellLog(SellLog sellLog) {
        this.sellLog = sellLog;
    }
    public static Seller getSellerWithName(String userName){
        for (Seller allSeller : allSellers) {
            if (allSeller.getUserName().equals(userName))
                return allSeller;

        }
        return null;
    }
    public static boolean isThereSellerWithUserName(String userName){
        for (Seller seller : allSellers) {
            if(seller.getUserName().equals(userName)){
                return true;
            }
        }
        return false;
    }
    public static void deleteSeller(String username){
        allSellers.remove(getSellerByName(username));
    }
    public static Seller getSellerByName(String userName) {
        for (Seller seller : allSellers) {
            if(seller.getUserName().equals(userName))
                return seller;
        }
        return null;
    }
    public void removeProduct(Product product){
        allProducts.remove(product);
    }

    public String getCompanyName() {
        return companyName;
    }
    public boolean doesSellerHaveThisProduct(Product product){
        for (Product allProducts : this.allProducts) {
            if(allProducts.equals(product)){
                return true;
            }
        }
        return false;
    }
    @Override
    public String toString() {
        return "Seller{" +
                "username='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", credit='" + credit + '\'' +
                "companyName='" + companyName + '\'' +
                '}';
    }
}
