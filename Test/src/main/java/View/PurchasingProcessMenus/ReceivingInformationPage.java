package View.PurchasingProcessMenus;

import Models.Accounts.Customer;
import View.Menu;
import View.RegisterCustomerMenu;
import View.RegisterSellerMenu;

import java.io.FileNotFoundException;

public class ReceivingInformationPage extends Menu {
    DiscountCodePage discountCodePage = new DiscountCodePage(this);
    private static String address;
    private static String phoneNum;

    public ReceivingInformationPage(Menu parentMenu) {
        super("Receiver Information", parentMenu);
    }

    @Override
    public void show() {
        System.out.println("you are in receiving information page");
        System.out.println("1.complete my information");
        System.out.println("2.back");

    }



    public void receivingInformation() throws FileNotFoundException {
        String command;
        Customer customer = RegisterCustomerMenu.getCurrentCustomer();
        System.out.println("if you are sure you want to continue this process enter yes\n " + "enter back to go back to previous page");
        while (true) {
            command = scanner.nextLine();
            if (command.equals("yes")) {
                System.out.println("Enter address:");
                address = scanner.nextLine();
                System.out.println("Enter phone number:");
                phoneNum = scanner.nextLine();
                System.out.println("enter done to confirm the information\n " + "enter change to re enter the information");
                String option = scanner.nextLine();
                if (option.equals("done")) {
                    discountCodePage.show();
                   // discountCodePage.execute();
                } else if (option.equals("change")) {
                    receivingInformation();
                }
            } else if (command.equals("back")) {
                break;
            }
        }
    }

    public static String getAddress() {
        return address;
    }

    public static String getPhoneNum() {
        return phoneNum;
    }
}