package Server.ServerController;

import Models.Accounts.Customer;
import Models.Accounts.Seller;
import Server.ClientHandler;
import Server.ServerController.AccountsController.SellerController;

public class WalletController {
    public void customerWalletCharge() {
        Object[] toReceive = (Object[]) ClientHandler.receiveObject();
        Customer customer = (Customer) toReceive[0];
        double amount = (double) toReceive[1];

        if (customer != null) {
            ClientHandler.sendObject("OK");
        } else {
            ClientHandler.sendObject(new Exception("customer should first login"));
        }
    }

    public void sellerWalletCharge() {
        Object[] toReceive = (Object[]) ClientHandler.receiveObject();
        Customer customer = (Customer) toReceive[0];
        double amount = (double) toReceive[1];

        if (customer != null) {
            ClientHandler.sendObject("OK");
        } else {
            ClientHandler.sendObject(new Exception("customer should first login"));
        }
    }

    public void sellerWithdrawalWallet() {
        Object[] toReceive = (Object[]) ClientHandler.receiveObject();
        Customer customer = (Customer) toReceive[0];
        double amount = (double) toReceive[1];

        if (customer != null) {
            if (SellerController.getSeller().getWallet().canDecreaseAmount(amount)) {
                ClientHandler.sendObject("OK");
            } else {
                ClientHandler.sendObject("can't decrease");
            }
        } else {
            ClientHandler.sendObject(new Exception("customer should first login"));
        }
    }

}
