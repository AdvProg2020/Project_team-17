package Client.ClientController;

import Client.Client;

public class WalletController {

    public void customerWalletCharge() {
        String func = "Charge Customer Wallet";
        Client.sendMessage(func);
    }

    public void sellerWalletCharge() {
        String func = "Charge Seller Wallet";
        Client.sendMessage(func);
    }
}