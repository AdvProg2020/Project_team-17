package View.PurchasingProcessMenus;

import View.Menu;

import java.io.FileNotFoundException;

public class PurchaseMenu extends Menu {
    ReceivingInformationPage receivingInformationPage = new ReceivingInformationPage(this);

    public PurchaseMenu(Menu parentMenu) {
        super("Purchase", parentMenu);
    }

    @Override
    public void show() {
        System.out.println("you are processing your order");
        System.out.println("enter next to go to receiving information page");
    }


}
