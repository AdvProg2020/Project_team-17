package Server.ServerController;

import Models.Accounts.Customer;
import Models.Accounts.Seller;

public class WalletController {
    public void customerWalletCharge(Customer customer, double amount) {
        customer.getWallet().setBalance(customer.getWallet().getBalance() + amount);
    }

    public void sellerWalletCharge(Seller seller, double amount) {
        seller.getWallet().setBalance(seller.getWallet().getBalance() + amount);
    }

}