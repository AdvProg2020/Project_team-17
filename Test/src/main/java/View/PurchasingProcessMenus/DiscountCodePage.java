package View.PurchasingProcessMenus;

import Controller.AccountsManager.CustomerAbilitiesManager;
import Models.DiscountCode;
import View.Menu;

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
            paymentPage.show();
            paymentPage.execute();
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
                DiscountCode.getDiscountCodeWithCode(code).setUsageOfDiscountCode();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String getCodeOfDiscountCode() {
        return discountCode;
    }

    public static DiscountCode getDiscountCode() {
        return DiscountCode.getDiscountCodeWithCode(getCodeOfDiscountCode());
    }
}