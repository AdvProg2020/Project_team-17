package View.PurchasingProcessMenus;

import Controller.AccountsManager.CustomerAbilitiesManager;
import Models.Accounts.Customer;
import View.CommandProcessor;
import View.Menu;
import View.RegisterAndLoginMenu;

public class PaymentPage extends Menu {
    public PaymentPage(Menu parentMenu) {
        super("Payment", parentMenu);
    }
    CommandProcessor commandProcessor = new CommandProcessor();

    @Override
    public void show() {
        System.out.println("you are in payment page");
        System.out.println("1.finish your purchase");
        System.out.println("2.back");
    }

    @Override
    public void execute() {
        int input = Integer.parseInt(scanner.nextLine());
        if (input == 1) {
            pay();
            commandProcessor.runWithMenu();
        } else if (input == 2) {
            parentMenu.show();
            parentMenu.execute();
        }
    }

    private void pay() {
        Customer customer = RegisterAndLoginMenu.getCurrentCustomer();
        try {
            CustomerAbilitiesManager.checkAndPay(customer, DiscountCodePage.getDiscountCode());
            System.out.println("Finish");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}