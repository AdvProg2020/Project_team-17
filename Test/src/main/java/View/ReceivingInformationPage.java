package View;

import Models.Accounts.Customer;

public class ReceivingInformationPage extends Menu {
    private static String address;
    private static String phoneNum;
    private static String date;
    public ReceivingInformationPage( Menu parentMenu) {
        super("Receiver Information", parentMenu);
    }

    @Override
    public void show() {
        System.out.println("you are in receiving information page");
        System.out.println("1.complete my information");
        System.out.println("2.back");

    }

    @Override
    public void execute() {
        int input= Integer.parseInt(scanner.nextLine());
        if(input ==1){
            receivingInformation();
        }else if(input == 2){
            parentMenu.show();
            parentMenu.execute();
        }

    }
    public void receivingInformation(){
        String command;
        Customer customer=RegisterAndLoginMenu.getCurrentCustomer();
        System.out.println("if you are sure you want to continue this process enter yes\n "+"enter back to go back to previous page");
        while(true) {
            command=scanner.nextLine();
            if(command.equals("yes")) {
                System.out.println("Enter address:");
                address = scanner.nextLine();
                System.out.println("Enter phone number:");
                phoneNum = scanner.nextLine();
                System.out.println("Enter date");
                date=scanner.nextLine();
                System.out.println("enter done to confirm the information\n " + "enter change to re enter the information");
                String option = scanner.nextLine();
                if (option.equals("confirm")) {
                    new DiscountCodePage(this);
                } else if (option.equals("change")) {
                    receivingInformation();
                }
            }else if(command.equals("back")){
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

    public static String getDate() {
        return date;
    }
}
