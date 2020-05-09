package View;
import Controller.AccountsManager.ManagerAbilitiesManager;
import Models.Accounts.Manager;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManagerMenu extends Menu {
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
        Manager manager= RegisterAndLoginMenu.getCurrentManager();
        while (true) {
            command = scanner.nextLine();
            Pattern editFieldPattern=Pattern.compile("edit\\s(.+)");
            Matcher editFieldMatcher= editFieldPattern.matcher(command);
            if (command.matches("edit\\s(.+)")) {
                editFieldMatcher.find();
                System.out.println("what is the new content for this field?");
                String newContent=scanner.nextLine();
                ManagerAbilitiesManager.changeField(manager,editFieldMatcher.group(1),newContent);
            }
            if (command.equals("help")) {
                System.out.println("commands that you can enter are:");
                System.out.println("edit [field]");
                System.out.println("back");
            } else if (command.equals("back")) {
                break;
            } else System.out.println("Command is invalid");
        }
    }
    public void manageUsers(){
        String command;
        System.out.println(ManagerAbilitiesManager.showAllAccounts());
        while(true) {
            command = scanner.nextLine();
            Pattern viewAccountPattern = Pattern.compile("view\\s(.+)");
            Matcher viewAccountMatcher = viewAccountPattern.matcher(command);
            Pattern deleteAccountPattern = Pattern.compile("delete user\\s(.+)");
            Matcher deleteAccountMatcher = deleteAccountPattern.matcher(command);
            if(command.matches("view\\s(.+)")){
                viewAccountMatcher.find();
                System.out.println(ManagerAbilitiesManager.viewAccountByUsername(viewAccountMatcher.group(1)));
            }else if(command. matches("delete user\\s(.+)")){
                deleteAccountMatcher.find();
                ManagerAbilitiesManager.deleteUser(deleteAccountMatcher.group(1));
            }else if(command.equals("create manager profile")){
                getInfoForCreatingManger();
            }
        }
    }
    public void manageAllProducts(){
        String command;
        while(true){
            command = scanner.nextLine();
            Pattern removeProductPattern= Pattern.compile("remove product\\s(.+)");
            Matcher removeProductMatcher=removeProductPattern.matcher(command);
            if(command.matches("remove product\\s(.+)")){
                removeProductMatcher.find();
                try {
                    ManagerAbilitiesManager.removeProduct(removeProductMatcher.group(1));
                    System.out.println("Product removed successfully");
                }catch (Exception e){
                    e.getMessage();
                }
            }else if(command.equals("back")){
                break;
            }else if(command.equals("help")){
                System.out.println("commands that you can enter are:");
                System.out.println("remove product [productID]");
                System.out.println("back");
            }
        }
    }
    public void createDiscountCode(){
    }
    public void viewDiscountCodes(){
    }
    public void manageRequests(){
    }
    public void manageCategories(){
        String command;
        System.out.println(ManagerAbilitiesManager.showAllCategories());
        while (true){
            command = scanner.nextLine();
            Pattern editCategoryPattern= Pattern.compile("edit\\s(.+)");
            Matcher editCategoryMatcher=editCategoryPattern.matcher(command);
            Pattern addCategoryPattern =Pattern.compile("add\\s(.+)");
            Matcher addCategoryMatcher = addCategoryPattern.matcher(command);
            Pattern removeCategoryPattern =Pattern.compile("remove\\s(.+)");
            Matcher removeCategoryMatcher = removeCategoryPattern.matcher(command);
            if(command.matches("edit\\s(.+)")){
                editCategoryMatcher.find();
                System.out.println("you want to edit name or feature?");
                String input= scanner.nextLine();
                if(input.equals("name")){
                    System.out.println("enter the new name :");
                    String newName= scanner. nextLine();
                    ManagerAbilitiesManager.editCategoryName(editCategoryMatcher.group(1),newName);
                    System.out.println("name edited successfully");
                }else if(input.equals("feature")){
                    System.out.println("enter the new feature :");
                    String newFeature = scanner.nextLine();
                    ManagerAbilitiesManager.editCategoryFeature(editCategoryMatcher.group(1),newFeature);
                    System.out.println("feature edited successfully");
                }
            }else if(command.matches("add\\s(.+)")){
                addCategoryMatcher.find();
                System.out.println("enter the feature of new category :");
                String feature = scanner.nextLine();
                ManagerAbilitiesManager.addCategory(addCategoryMatcher.group(1),feature);
                System.out.println("new category added successfully");
            }else if(command.matches("remove\\s(.+)")){
                removeCategoryMatcher.find();
                ManagerAbilitiesManager.removeCategory(removeCategoryMatcher.group(1));
            }else if(command.equals("back")){
                break;
            }else if(command.equals("help")){
                System.out.println("commands that you can enter are:");
                System.out.println("edit [category]");
                System.out.println("remove [category]");
                System.out.println("add [category]");
                System.out.println("back");
            }
        }
    }
    public void getInfoForCreatingManger(){
        ArrayList<String> accountInfo = new ArrayList<>();
        System.out.println("Enter username: ");
        String username= scanner.nextLine();
        try {
            ManagerAbilitiesManager.isThereAccountWithThisUsername(username);
            System.out.println("Enter password:");
            accountInfo.add(scanner.nextLine());
            System.out.println("Enter your first name:");
            accountInfo.add(scanner.nextLine());
            System.out.println("Enter your last name:");
            accountInfo.add(scanner.nextLine());
            System.out.println("Enter your email:");
            accountInfo.add(scanner.nextLine());
            System.out.println("Enter your phone number:");
            accountInfo.add(scanner.nextLine());
            ManagerAbilitiesManager.createAnotherManager(username,accountInfo.get(1),accountInfo.get(2),accountInfo.get(3),accountInfo.get(4),accountInfo.get(0));
            }catch (Exception e){
                e.getMessage();
            }
        }
    }
