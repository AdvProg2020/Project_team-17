package View.PurchasingProcessMenus;

import Controller.AccountsManager.CustomerAbilitiesManager;
import Controller.CartManager;
import Models.Accounts.Customer;
import View.Menu;
import View.RegisterAndLoginMenu;

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

    @Override
    public void execute() {
        int input = Integer.parseInt(scanner.nextLine());
        if (input == 1) {
            showFinalPrice();
            this.show();
            this.execute();
        } else if (input == 2) {
            pay();
            menu.show();
            menu.execute();
            //commandProcessor.runWithMenu();
        } else if (input == 3) {
            parentMenu.show();
            parentMenu.execute();
        }
    }

    public void showFinalPrice() {
        Customer customer = RegisterAndLoginMenu.getCurrentCustomer();
        System.out.println(CartManager.showTotalPriceOfCart(customer, DiscountCodePage.getDiscountCode()));
    }

    private void pay() {
        Customer customer = RegisterAndLoginMenu.getCurrentCustomer();
        /*try {
            CustomerAbilitiesManager.finalPay(customer, DiscountCodePage.getCodeOfDiscountCode());
            System.out.println("Finish");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/
        System.out.println(CustomerAbilitiesManager.finalPay(customer, DiscountCodePage.getCodeOfDiscountCode()));
    }
}