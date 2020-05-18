package View;

import Controller.AccountsManager.CustomerAbilitiesManager;
import Models.Accounts.Customer;

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
            commandProcessor.runWithMenu();
        } catch (Exception e) {
            e.getMessage();
        }
    }
}