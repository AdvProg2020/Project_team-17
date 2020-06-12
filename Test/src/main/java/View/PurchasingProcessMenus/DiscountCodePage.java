package View.PurchasingProcessMenus;

import Controller.AccountsManager.CustomerAbilitiesManager;
import Models.DiscountCode;
import View.Menu;

import java.io.FileNotFoundException;

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