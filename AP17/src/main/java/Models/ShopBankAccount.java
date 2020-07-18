package Models;

import Models.Bank.BankAccount;

public class ShopBankAccount {
    private static ShopBankAccount shopBankAccount = new ShopBankAccount();

    private ShopBankAccount() {
        new BankAccount("shop", "shop", "shop", "1234");
    }

    public static ShopBankAccount getInstance() {
        return shopBankAccount;
    }

}
