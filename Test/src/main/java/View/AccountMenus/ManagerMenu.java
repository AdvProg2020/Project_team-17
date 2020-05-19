package View.AccountMenus;

import Controller.AccountsManager.ManagerAbilitiesManager;
import Models.Accounts.Manager;
import Models.Category;
import View.CommandProcessor;
import View.Menu;
import View.RegisterAndLoginMenu;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManagerMenu extends Menu {
    RegisterAndLoginMenu registerAndLoginMenu = new RegisterAndLoginMenu(this);
    CommandProcessor commandProcessor = new CommandProcessor();

    public ManagerMenu(Menu parentMenu) {
        super("Manager ", parentMenu);
    }

    @Override
    public void show() {
        if (RegisterAndLoginMenu.getCurrentManager() != null) {
            System.out.println("1.view personal info");
            System.out.println("2.manage users");
            System.out.println("3.manage all products");
            System.out.println("4.create discount code");
            System.out.println("5.view discount codes");
            System.out.println("6.manage requests");
            System.out.println("7.manage categories");
            System.out.println("8.logout");
            System.out.println("9.back");
        } else {
            System.out.println("you haven't logged in yet, first login as a manager");
            registerAndLoginMenu.show();
            registerAndLoginMenu.execute();
        }
    }

    @Override
    public void execute() {
        try {
            int input = Integer.parseInt(scanner.nextLine());
            if (input == 1) {
                viewPersonalInfo();
                parentMenu.show();
                parentMenu.execute();
            } else if (input == 2) {
                manageUsers();
                parentMenu.show();
                parentMenu.execute();
            } else if (input == 3) {
                manageAllProducts();
                parentMenu.show();
                parentMenu.execute();
            } else if (input == 4) {
                createDiscountCode();
                parentMenu.show();
                parentMenu.execute();
            } else if (input == 5) {
                viewDiscountCodes();
                parentMenu.show();
                parentMenu.execute();
            } else if (input == 6) {
                manageRequests();
                parentMenu.show();
                parentMenu.execute();
            } else if (input == 7) {
                manageCategories();
                parentMenu.show();
                parentMenu.execute();
            } else if (input == 8) {
                RegisterAndLoginMenu.logout();
                commandProcessor.runWithMenu();
            } else if (input == 9) {
                parentMenu.show();
                parentMenu.execute();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    public void viewPersonalInfo() {
        String command;
        Manager manager = RegisterAndLoginMenu.getCurrentManager();
        System.out.println(manager.toString());
        while (true) {
            command = scanner.nextLine();
            Pattern editFieldPattern = Pattern.compile("edit\\s(.+)");
            Matcher editFieldMatcher = editFieldPattern.matcher(command);
            if (command.matches("edit\\s(.+)")) {
                editFieldMatcher.find();
                System.out.println("what is the new content for this field?");
                String newContent = scanner.nextLine();
                ManagerAbilitiesManager.changeField(manager, editFieldMatcher.group(1), newContent);
            } else if (command.equals("help")) {
                System.out.println("commands that you can enter are:");
                System.out.println("edit [field]");
                System.out.println("back");
            } else if (command.equals("back")) {
                break;
            } else System.out.println("Command is invalid");
        }
    }

    public void manageUsers() {
        String command;
        System.out.println(ManagerAbilitiesManager.showAllAccounts());
        while (true) {
            command = scanner.nextLine();
            Pattern viewAccountPattern = Pattern.compile("view\\s(.+)");
            Matcher viewAccountMatcher = viewAccountPattern.matcher(command);
            Pattern deleteAccountPattern = Pattern.compile("delete user\\s(.+)");
            Matcher deleteAccountMatcher = deleteAccountPattern.matcher(command);
            if (command.matches("view\\s(.+)")) {
                viewAccountMatcher.find();
                try {
                    System.out.println(ManagerAbilitiesManager.viewAccountByUsername(viewAccountMatcher.group(1)));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.matches("delete user\\s(.+)")) {
                deleteAccountMatcher.find();
                try {
                    ManagerAbilitiesManager.deleteUser(deleteAccountMatcher.group(1));
                    System.out.println("user deleted successfully");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.equals("create manager profile")) {
                getInfoForCreatingManger();
                System.out.println("manager created successfully");
            } else if (command.equals("back")) {
                break;
            } else if (command.equals("help")) {
                System.out.println("commands that you can enter are:");
                System.out.println("view [username]");
                System.out.println("delete user [username]");
                System.out.println("create manager profile");
                System.out.println("back");
            }
        }
    }

    public void manageAllProducts() {
        String command;
        while (true) {
            command = scanner.nextLine();
            Pattern removeProductPattern = Pattern.compile("remove product\\s(.+)");
            Matcher removeProductMatcher = removeProductPattern.matcher(command);
            if (command.matches("remove product\\s(.+)")) {
                removeProductMatcher.find();
                try {
                    ManagerAbilitiesManager.removeProduct(removeProductMatcher.group(1));
                    System.out.println("Product removed successfully");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.equals("back")) {
                break;
            } else if (command.equals("help")) {
                System.out.println("commands that you can enter are:");
                System.out.println("remove product [productID]");
                System.out.println("back");
            }
        }
    }

    public void createDiscountCode() {
        String command;
        while (true) {
            command = scanner.nextLine();
            if (command.equals("create discount code")) {
                getInfoForCreatingDiscountCode();
            } else if (command.equals("back")) {
                break;
            } else if (command.equals("help")) {
                System.out.println("commands that you can enter are:");
                System.out.println("create discount code");
                System.out.println("back");
            } else {
                System.out.println("invalid command");
            }
        }
    }

    public void viewDiscountCodes() {
        String command;
        System.out.println(ManagerAbilitiesManager.viewDiscountCodes());
        while (true) {
            command = scanner.nextLine();
            Pattern viewDiscountCodePattern = Pattern.compile("view discount code\\s(.+)");
            Matcher viewDiscountCodeMatcher = viewDiscountCodePattern.matcher(command);
            Pattern editDiscountCodePattern = Pattern.compile("edit discount code\\s(.+)");
            Matcher editDiscountCodeMatcher = editDiscountCodePattern.matcher(command);
            Pattern removeDiscountCodePattern = Pattern.compile("remove discount code\\s(.+)");
            Matcher removeDiscountCodeMatcher = removeDiscountCodePattern.matcher(command);
            if (command.matches("view discount code\\s(.+)")) {
                viewDiscountCodeMatcher.find();
                try {
                    ManagerAbilitiesManager.isThereDiscountCode(viewDiscountCodeMatcher.group(1));
                    System.out.println(ManagerAbilitiesManager.viewDiscountCode(viewDiscountCodeMatcher.group(1)));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.matches("edit discount code\\s(.+)")) {
                editDiscountCodeMatcher.find();
                try {
                    ManagerAbilitiesManager.isThereDiscountCode(editDiscountCodeMatcher.group(1));
                    System.out.println("enter the field you want to edit: ");
                    String field = scanner.nextLine();
                    System.out.println("enter new content for this field: ");
                    String newContent = scanner.nextLine();
                    ManagerAbilitiesManager.editDiscountCode(editDiscountCodeMatcher.group(1), field, newContent);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.matches("remove discount code\\s(.+)")) {
                removeDiscountCodeMatcher.find();
                try {
                    ManagerAbilitiesManager.isThereDiscountCode(removeDiscountCodeMatcher.group(1));
                    ManagerAbilitiesManager.removeDiscountCode(removeDiscountCodeMatcher.group(1));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.equals("back")) {
                break;
            } else if (command.equals("help")) {
                System.out.println("commands that you can enter are:");
                System.out.println("view discount code [code]");
                System.out.println("edit discount code [code]");
                System.out.println("remove discount code [code]");
                System.out.println("back");
            }

        }
    }

    public void manageRequests() {
        String command;
        System.out.println(ManagerAbilitiesManager.showAllRequests());
        while (true) {
            command = scanner.nextLine();
            Pattern detailsOfRequestPattern = Pattern.compile("details\\s(.+)");
            Matcher detailsOfRequestMatcher = detailsOfRequestPattern.matcher(command);
            Pattern acceptRequestPattern = Pattern.compile("accept\\s(.+)");
            Matcher acceptRequestMatcher = acceptRequestPattern.matcher(command);
            Pattern declineRequestPattern = Pattern.compile("decline\\s(.+)");
            Matcher declineRequestMatcher = declineRequestPattern.matcher(command);
            if (command.matches("details\\s(.+)")) {
                detailsOfRequestMatcher.find();
                try {
                    System.out.println(ManagerAbilitiesManager.showDetailsOfRequest(detailsOfRequestMatcher.group(1)));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.matches("accept\\s(.+)")) {
                acceptRequestMatcher.find();
                try {
                    System.out.println(ManagerAbilitiesManager.acceptRequest(acceptRequestMatcher.group(1)));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.matches("decline\\s(.+)")) {
                declineRequestMatcher.find();
                try {
                    ManagerAbilitiesManager.declineRequest(declineRequestMatcher.group(1));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.equals("help")) {
                System.out.println("commands that you can enter are:");
                System.out.println("details [requestID]");
                System.out.println("accept [requestID]");
                System.out.println("decline [requestID]");
            } else if (command.equals("back")) {
                break;
            }
        }
    }

    public void manageCategories() {
        String command;
        System.out.println(ManagerAbilitiesManager.showAllCategories());
        while (true) {
            command = scanner.nextLine();
            Pattern editCategoryPattern = Pattern.compile("edit\\s(.+)");
            Matcher editCategoryMatcher = editCategoryPattern.matcher(command);
            Pattern addCategoryPattern = Pattern.compile("add\\s(.+)");
            Matcher addCategoryMatcher = addCategoryPattern.matcher(command);
            Pattern removeCategoryPattern = Pattern.compile("remove\\s(.+)");
            Matcher removeCategoryMatcher = removeCategoryPattern.matcher(command);
            if (command.matches("edit\\s(.+)")) {
                editCategoryMatcher.find();
                Category category = Category.getCategoryByName(editCategoryMatcher.group(1));
                if (category != null) {
                    System.out.println("you want to edit name or feature?(enter name or feature)");
                    String input = scanner.nextLine();
                    if (input.equals("name")) {
                        System.out.println("enter the new name :");
                        String newName = scanner.nextLine();
                        ManagerAbilitiesManager.editCategoryName(category, newName);
                        System.out.println("name edited successfully");
                    } else if (input.equals("feature")) {
                        System.out.println("enter the new feature :");
                        String newFeature = scanner.nextLine();
                        ManagerAbilitiesManager.editCategoryFeature(category, newFeature);
                        System.out.println("feature edited successfully");
                    }
                } else if (category == null) {
                    System.out.println("there isn't category with this name");
                }

            } else if (command.matches("add\\s(.+)")) {
                addCategoryMatcher.find();
                System.out.println("enter the feature of new category :");
                String feature = scanner.nextLine();
                try {
                    ManagerAbilitiesManager.addCategory(addCategoryMatcher.group(1), feature);
                    System.out.println("new category added successfully");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.matches("remove\\s(.+)")) {
                removeCategoryMatcher.find();
                try {
                    ManagerAbilitiesManager.removeCategory(removeCategoryMatcher.group(1));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.equals("back")) {
                break;
            } else if (command.equals("help")) {
                System.out.println("commands that you can enter are:");
                System.out.println("edit [category]");
                System.out.println("remove [category]");
                System.out.println("add [category]");
                System.out.println("back");
            }
        }
    }

    public void getInfoForCreatingManger() {
        ArrayList<String> accountInfo = new ArrayList<>();
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
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
            ManagerAbilitiesManager.createAnotherManager(username, accountInfo.get(1), accountInfo.get(2), accountInfo.get(3), accountInfo.get(4), accountInfo.get(0));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void getInfoForCreatingDiscountCode() {
        ArrayList<String> customersName = new ArrayList<>();
        System.out.println("Enter code: ");
        String code = scanner.nextLine();
        System.out.println("Enter beginning date(yyyy-mm-dd): ");
        String startDate = scanner.nextLine();
        System.out.println("Enter ending date(yyyy-mm-dd): ");
        String endDate = scanner.nextLine();
        System.out.println("Enter discount percent: ");
        String discountPercent = scanner.nextLine();
        System.out.println("Enter maximum discount amount :");
        String maxAmountForDiscount = scanner.nextLine();
        System.out.println("Enter number of repeat for this discount code: ");
        String repeat = scanner.nextLine();

        System.out.println("how many customer have this code: ");
        int numOfCustomer = Integer.valueOf(scanner.nextLine());
        for (int i = 0; i < numOfCustomer; i++) {
            System.out.println("enter the name of customer:");
            String name = scanner.nextLine();
            customersName.add(name);
        }
        ManagerAbilitiesManager.createDiscountCode(code, startDate, endDate, discountPercent, maxAmountForDiscount, Integer.parseInt(repeat), customersName);
    }


}
