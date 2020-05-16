package View;

import Controller.RegisterAndLoginManager;
import Models.Accounts.Customer;
import Models.Accounts.Manager;
import Models.Accounts.Seller;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterAndLoginMenu extends Menu {
    public static Customer currentCustomer;
    public static Seller currentSeller;
    public static Manager currentManager;

    public RegisterAndLoginMenu(Menu parentMenu) {
        super("Register And Login ", parentMenu);
    }

    @Override
    public void show() {
        System.out.println("1.Register");
        System.out.println("2.Login");
        System.out.println("3.Back");
    }

    @Override
    public void execute() {
        int input = Integer.parseInt(scanner.nextLine());
        if (input == 1) {
            register();
            parentMenu.show();
            parentMenu.execute();
        } else if (input == 2) {
            login();
            parentMenu.show();
            parentMenu.execute();
        } else if (input == 3) {
            parentMenu.show();
            parentMenu.execute();
        }

    }

    public void register() {
        String command;
        while (true) {
            ArrayList<String> accountInfo = new ArrayList<>();
            command = scanner.nextLine();
            Pattern registerPattern = Pattern.compile("create account\\s(customer|seller|manager)\\s(.+)");
            Matcher registerMatcher = registerPattern.matcher(command);
            if (command.matches("create account\\s(customer|seller|manager)\\s(.+)")) {
                registerMatcher.find();
                if ((!(Customer.isThereCustomerWithUserName(registerMatcher.group(2)))) &&
                        (!(Seller.isThereSellerWithUserName(registerMatcher.group(2)))) &&
                        (!(Manager.isThereManagerWithUserName(registerMatcher.group(2))))) {
                    System.out.println("Enter password:");
                    accountInfo.add(scanner.nextLine());
                    System.out.println("Enter your first name:");
                    accountInfo.add(scanner.nextLine());
                    System.out.println("Enter your last name:");
                    accountInfo.add(scanner.nextLine());
                    System.out.println("Enter your email:");
                    accountInfo.add(scanner.nextLine());
                    System.out.println("Enter your address:");
                    accountInfo.add(scanner.nextLine());
                    System.out.println("Enter your phone number:");
                    accountInfo.add(scanner.nextLine());
                    if (registerMatcher.group(1).equals("customer")) {
                        new Customer(registerMatcher.group(2), accountInfo.get(1), accountInfo.get(2), accountInfo.get(3),
                                accountInfo.get(5), accountInfo.get(0), 0, null);
                    } else if (registerMatcher.group(1).equals("seller")) {
                        System.out.println("Enter your company's name:");
                        accountInfo.add(scanner.nextLine());
                        new Seller(registerMatcher.group(2), accountInfo.get(1), accountInfo.get(2), accountInfo.get(3),
                                accountInfo.get(5), accountInfo.get(0), 0, accountInfo.get(6));
                    } else if (registerMatcher.group(1).equals("manager")) {
                        new Manager(registerMatcher.group(2), accountInfo.get(1), accountInfo.get(2), accountInfo.get(3),
                                accountInfo.get(5), accountInfo.get(0));
                    }
                    System.out.println(registerMatcher.group(1) + " registered successfully");
                } else {
                    System.out.println("There is an account with this username!");
                }
            } else if (command.equals("help")) {
                System.out.println("commands that you can enter are:");
                System.out.println("create account [type] [username]");
                System.out.println("Back");
            } else if (command.equals("back")) {
                break;
            } else {
                System.out.println("Command is Invalid");
            }
        }
    }

    public void login() {
        String command;
        while (true) {
            command = scanner.nextLine();
            Pattern loginPattern = Pattern.compile("login\\s(.+)");
            Matcher loginMatcher = loginPattern.matcher(command);
            if (command.matches("login\\s(.+)")) {
                loginMatcher.find();
                if (Customer.isThereCustomerWithUserName(loginMatcher.group(1))) {
                    Customer customer = Customer.getCustomerByName(loginMatcher.group(1));
                    System.out.println("Enter your password: ");
                    String password = scanner.nextLine();
                    try {
                        RegisterAndLoginManager.isUserNameAndPasswordCorrectForCustomer(loginMatcher.group(1), password);
                        currentCustomer = customer;
                        //in k be safhe ghabli k mikhaste bere ro bayad handle konim
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else if (Seller.isThereSellerWithUserName(loginMatcher.group(1))) {
                    Seller seller = Seller.getSellerByName(loginMatcher.group(1));
                    System.out.println("Enter your password: ");
                    String password = scanner.nextLine();
                    try {
                        RegisterAndLoginManager.isUserNameAndPasswordCorrectForSeller(loginMatcher.group(1), password);
                        currentSeller = seller;
                        //in k be safhe ghabli k mikhaste bere ro bayad handle konim
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else if (Manager.isThereManagerWithUserName(loginMatcher.group(1))) {
                    Manager manager = Manager.getManagerByUserName(loginMatcher.group(1));
                    System.out.println("Enter your password: ");
                    String password = scanner.nextLine();
                    try {
                        RegisterAndLoginManager.isUserNameAndPasswordCorrectForManager(loginMatcher.group(1), password);
                        currentManager = manager;
                        //in k be safhe ghabli k mikhaste bere ro bayad handle konim
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    System.out.println("There isn't any account registered with this username");
                }
            } else if (command.equals("help")) {
                System.out.println("commands that you can enter are:");
                System.out.println("login [username]");
                System.out.println("Back");
            } else if (command.equals("back")) {
                break;
            } else {
                System.out.println("Command is Invalid");
            }

        }
    }

    public static Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public static Seller getCurrentSeller() {
        return currentSeller;
    }

    public static Manager getCurrentManager() {
        return currentManager;
    }
}