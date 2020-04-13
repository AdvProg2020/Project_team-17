package Models;

import java.util.ArrayList;

public class Seller extends Account {
    private String companyName;
    private static ArrayList<Seller> allSellers= new ArrayList<Seller>();
    private ArrayList<SellLog> sellLogs;

    public Seller(String userName, String firstName, String lastName, String email, String phoneNumber, String password) {
        super(userName, firstName, lastName, email, phoneNumber, password);
        this.companyName=companyName;
        sellLogs = new ArrayList<SellLog>();
        allSellers.add(this);
    }
}
