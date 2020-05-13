package View;

public class PurchaseMenu extends Menu {
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
        String input= scanner.nextLine();
        if(input.equals("next")){
            new ReceivingInformationPage(this);
        }else if(input.equals("back")){
            parentMenu.show();
            parentMenu.execute();
        }
    }


}
