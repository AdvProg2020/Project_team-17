package View;

import Controller.AccountsManager.SellerAbilitiesManager;
import Models.Accounts.Seller;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SellerMenu extends Menu {
    private SellerAbilitiesManager sellerAbilitiesManager;
    private RegisterAndLoginMenu registerAndLoginMenu;
    public SellerMenu( Menu parentMenu) {
        super("Seller ", parentMenu);
    }

    @Override
    public void show() {
        System.out.println("1.view personal info");
        System.out.println("2.view company information");
        System.out.println("3.view sales history");
        System.out.println("4.manage products");
        System.out.println("5.add product");
        System.out.println("6.remove product");
        System.out.println("7.show categories");
        System.out.println("8.view offs");
        System.out.println("9.view balance");
        System.out.println("10.back");
    }

    @Override
    public void execute() {
        int input = Integer.parseInt(scanner.nextLine());
        if (input == 1){
            viewPersonalInfo();
            parentMenu.show();
            parentMenu.execute();
        }else if (input == 2){
            viewCompanyInformation();
            parentMenu.show();
            parentMenu.execute();
        }else if (input == 3){
            viewSalesHistory();
            parentMenu.show();
            parentMenu.execute();
        }else if (input == 4){
            manageProducts();
            parentMenu.show();
            parentMenu.execute();
        }else if (input == 5){
            addProduct();
            parentMenu.show();
            parentMenu.execute();
        }else if (input == 6){
            removeProduct();
            parentMenu.show();
            parentMenu.execute();
        }else if (input == 7){
            showCategories();
            parentMenu.show();
            parentMenu.execute();
        }else if (input == 8){
            viewOffs();
            parentMenu.show();
            parentMenu.execute();
        }else if (input == 9){
            viewBalance();
            parentMenu.show();
            parentMenu.execute();
        }else if (input == 10){
            parentMenu.show();
            parentMenu.execute();
        }
    }

    public void viewPersonalInfo() {
        Seller seller =registerAndLoginMenu.getCurrentSeller();
        String command;
        while (true) {
            command = scanner.nextLine();
            Pattern editFieldPattern=Pattern.compile("edit\\s(.+)");
            Matcher editFieldMatcher= editFieldPattern.matcher(command);
            if (command.matches("edit\\s(.+)")) {
                editFieldMatcher.find();
                System.out.println("what is the new content for this field?");
                String newContent=scanner.nextLine();
                sellerAbilitiesManager.changeField(seller,editFieldMatcher.group(1),newContent);
            } else if (command.equals("help")) {
                System.out.println("commands that you can enter are:");
                System.out.println("edit [field]");
                System.out.println("back");
            } else if (command.equals("back")) {
                break;
            } else System.out.println("Command is invalid");
        }
    }
    public void viewCompanyInformation(){



    }
    public void viewSalesHistory(){

    }
    public void manageProducts(){

    }
    public void addProduct(){

    }
    public void removeProduct(){
    }
    public void showCategories(){

    }
    public void viewOffs(){

    }
    public void viewBalance(){

    }
}
