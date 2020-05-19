package View.PurchasingProcessMenus;

import Controller.AccountsManager.CustomerAbilitiesManager;
import View.Menu;
import View.PurchasingProcessMenus.PaymentPage;

public class DiscountCodePage extends Menu {
    PaymentPage paymentPage = new PaymentPage(this);
    private static String discountCode = "";

    public DiscountCodePage(Menu parentMenu) {
        super("Discount code", parentMenu);
    }

    @Override
    public void show() {
        System.out.println("you are in discount code page");
        System.out.println("do you have discount code?");
    }

    @Override
    public void execute() {
        String input = scanner.nextLine();
        if (input.equals("yes")) {
            discountCode();
        } else if (input.equals("no")) {
            paymentPage.show();
            paymentPage.execute();
        } else if (input.equals("back")) {
            //y soal ma alan kolan back k mizanim mirim ghabli parent ini k alan mirim tosh ini k tosh bodim k nemishe ya na mishe?
            parentMenu.show();
            parentMenu.execute();
        }
    }

    public void discountCode() {
        String code;
        while (true) {
            System.out.println("enter your discount code: ");
            code = scanner.nextLine();
            try {
                CustomerAbilitiesManager.checkDiscountCodeValidation(code);
                discountCode = code;
                paymentPage.show();
                paymentPage.execute();
                break;
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }

    public static String getDiscountCode() {
        return discountCode;
    }
}