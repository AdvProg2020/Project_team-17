package Client.ClientController;

import Client.Client;
import Server.ServerController.AccountsController.CustomerController;
import Server.ServerController.AccountsController.SellerController;

public class CWalletController {

    public static void customerWalletCharge(double amount) throws Exception {
        String func = "Customer Wallet Charge";
        Client.sendMessage(func);

        Object[] toSend = new Object[2];
        toSend[0] = CustomerController.getCustomer();
        toSend[1] = amount;
        Client.sendObject(toSend);
        try {
            Object response = Client.receiveObject();
            String string = (String) response;
            if (string.equals("OK")) {
                CustomerController.getCustomer().getWallet().setBalance(CustomerController.getCustomer().getWallet().getBalance() + amount);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void sellerWalletCharge(double amount) throws Exception {
        String func = "Seller Wallet Charge";
        Client.sendMessage(func);

        Object[] toSend = new Object[2];
        toSend[0] = SellerController.getSeller();
        toSend[1] = amount;
        Client.sendObject(toSend);

        try {
            Object response = Client.receiveObject();
            String string = (String) response;
            if (string.equals("OK")) {
                SellerController.getSeller().getWallet().setBalance(SellerController.getSeller().getWallet().getBalance() + amount);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void sellerWithdrawalWallet(double amount) throws Exception {
        String func = "Seller withdrawal Wallet";
        Client.sendMessage(func);

        Object[] toSend = new Object[2];
        toSend[0] = SellerController.getSeller();
        toSend[1] = amount;
        Client.sendObject(toSend);

        try {
            Object response = Client.receiveObject();
            String string = (String) response;
            if (string.equals("OK")) {
                SellerController.getSeller().getWallet().setBalance(SellerController.getSeller().getWallet().getBalance() - amount);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
