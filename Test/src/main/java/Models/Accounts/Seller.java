package Models.Accounts;

import Controller.AccountsManager.SellerAbilitiesManager;
import Models.Discount;
import Models.Product;
import Models.Logs.SellLog;

import java.util.ArrayList;

public class Seller extends Account {
    private String companyName;
    private ArrayList<Product> allProducts;
    private static ArrayList<Seller> allSellers = new ArrayList<Seller>();
    private ArrayList<Discount> allDiscount = new ArrayList<>();
    private ArrayList<SellLog> logs;

    public Seller(String userName, String firstName, String lastName, String email
            , String phoneNumber, String password, double credit, String companyName) {
        super(userName, firstName, lastName, email, phoneNumber, password, credit);
        this.companyName = companyName;
        allProducts = new ArrayList<>();
        logs = new ArrayList<>();
        allSellers.add(this);
    }

    public static Seller getSellerHasThisProduct(Product product) {
        for (Seller seller : allSellers) {
            for (Product allProduct : seller.getAllProducts()) {
                if (allProduct.equals(product)) {
                    return seller;
                }
            }
        }
        return null;
    }

    public void addLogToSellLog(SellLog sellLog) {
        this.logs.add(sellLog);
    }


    public ArrayList<Product> getAllProducts() {
        return allProducts;
    }

    public void addDiscountForSeller(Seller seller, Discount discount) {
        seller.allDiscount.add(discount);
    }

    public static ArrayList<Seller> getAllSellers() {
        return allSellers;
    }

    public ArrayList<String> getDiscountInfo(Seller seller) {
        ArrayList<String> allDiscountForSeller = new ArrayList<>();
        for (Discount discount : seller.allDiscount) {
            allDiscountForSeller.add("Discount ID: " + discount.getDiscountId() + " Discount percent: " + discount.getDiscountPercent());
        }
        return allDiscountForSeller;
    }

    public ArrayList<SellLog> getLogs() {
        return logs;
    }

    public static Seller getSellerWithName(String userName) {
        for (Seller allSeller : allSellers) {
            if (allSeller.getUserName().equals(userName))
                return allSeller;

        }
        return null;
    }

    public static boolean isThereSellerWithUserName(String userName) {
        for (Seller seller : allSellers) {
            if (seller.getUserName().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    public static void deleteSeller(String username) {
        allSellers.remove(getSellerByName(username));
    }

    public static Seller getSellerByName(String userName) {
        for (Seller seller : allSellers) {
            if (seller.getUserName().equals(userName))
                return seller;
        }
        return null;
    }

    public void addProduct(Seller seller, Product product) {
        seller.allProducts.add(product);
    }

    public void removeProduct(Seller seller, Product product) {
        seller.allProducts.remove(product);
    }

    public void addDiscount(Discount discount) {
        this.allDiscount.add(discount);
    }


    public String getCompanyName() {
        return companyName;
    }

    public boolean doesSellerHaveThisProduct(Product product) {
        for (Product allProducts : this.allProducts) {
            if (allProducts.equals(product)) {
                return true;
            }
        }
        return false;
    }

    public boolean isThereDiscountWithThisIdForSeller(Seller seller, String id) {
        for (Discount discount : seller.allDiscount) {
            if (discount.getDiscountId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void addMoneyToCredit(Product product) {
        this.credit += product.calculateProductPrice(product);
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