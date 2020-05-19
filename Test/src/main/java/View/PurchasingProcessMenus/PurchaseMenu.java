package View.PurchasingProcessMenus;

import View.Menu;

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

    @Override
    public void execute() {
        String input = scanner.nextLine();
        if (input.equals("next")) {
            receivingInformationPage.show();
            receivingInformationPage.execute();
        } else if (input.equals("back")) {
            parentMenu.show();
            parentMenu.execute();
        }
    }
}
