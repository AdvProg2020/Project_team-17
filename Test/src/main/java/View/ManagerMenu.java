package View;

import Controller.AccountsManager.ManagerAbilitiesManager;

public class ManagerMenu extends Menu {
    private ManagerAbilitiesManager managerAbilitiesManager;
    public ManagerMenu( Menu parentMenu) {
        super("Manager ", parentMenu);
    }

    @Override
    public void show() {
        System.out.println("1.view personal info");
        System.out.println("2.manage users");
        System.out.println("3.manage all products");
        System.out.println("4.create discount code");
        System.out.println("5.view discount codes");
        System.out.println("6.manage requests");
        System.out.println("7.manage categories");
        System.out.println("8.back");
    }

    @Override
    public void execute() {
        int input = Integer.parseInt(scanner.nextLine());
        if (input == 1){
            viewPersonalInfo();
            parentMenu.show();
            parentMenu.execute();
        }else if (input == 2){
            manageUsers();
            parentMenu.show();
            parentMenu.execute();
        }else if (input == 3){
            manageAllProducts();
            parentMenu.show();
            parentMenu.execute();
        }else if (input == 4){
            createDiscountCode();
            parentMenu.show();
            parentMenu.execute();
        }else if (input == 5){
            viewDiscountCodes();
            parentMenu.show();
            parentMenu.execute();
        }else if (input == 6){
            manageRequests();
            parentMenu.show();
            parentMenu.execute();
        }else if (input == 7){
            manageCategories();
            parentMenu.show();
            parentMenu.execute();
        }else if (input == 8){
            parentMenu.show();
            parentMenu.execute();
        }

    }
    public void viewPersonalInfo() {
        String command;
        while (true) {
            command = scanner.nextLine();
            if (command.equals("view personal info")) {
                //username i ke login karde(vase seller)
            } else if (command.equals("help")) {
                System.out.println("commands that you can enter are:");
                System.out.println("edit [field]");
                System.out.println("back");
            } else if (command.equals("back")) {
                break;
            } else System.out.println("Command is invalid");
        }
    }
    public void manageUsers(){

    }
    public void manageAllProducts(){

    }
    public void createDiscountCode(){

    }
    public void viewDiscountCodes(){

    }
    public void manageRequests(){

    }
    public void manageCategories(){

    }
}
