package Models;

import java.util.ArrayList;

public class Seller extends Account {
    private String companyName;
    private static ArrayList<Seller> allSellers= new ArrayList<Seller>();
    private SellLog sellLog;

    public Seller(String userName, String firstName, String lastName, String email, String phoneNumber, String password) {
        super(userName, firstName, lastName, email, phoneNumber, password);
        this.companyName=companyName;
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
}
