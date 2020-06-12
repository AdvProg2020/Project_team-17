package View.PurchasingProcessMenus;

import Controller.AccountsManager.CustomerAbilitiesManager;
import Controller.CartManager;
import Models.Accounts.Customer;
import View.Menu;
import View.RegisterCustomerMenu;
import View.RegisterSellerMenu;

import java.io.FileNotFoundException;

public class PaymentPage extends Menu {
    public PaymentPage(Menu parentMenu) {
        super("Payment", parentMenu);
    }

    Menu menu = Menu.getMenu("Main Menu");

    @Override
    public void show() {
        System.out.println("you are in payment page");
        System.out.println("1.show total price:");
        System.out.println("2.finish your purchase");
        System.out.println("3.back");
    }


    public void showFinalPrice() {
        Customer customer = RegisterCustomerMenu.getCurrentCustomer();
        System.out.println(CartManager.showTotalPriceOfCart(customer, DiscountCodePage.getDiscountCode()));
    }

    private void pay() {
        Customer customer = RegisterCustomerMenu.getCurrentCustomer();
        /*try {
            CustomerAbilitiesManager.finalPay(customer, DiscountCodePage.getCodeOfDiscountCode());
            System.out.println("Finish");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/
        System.out.println(CustomerAbilitiesManager.finalPay(customer, DiscountCodePage.getCodeOfDiscountCode()));
    }
}