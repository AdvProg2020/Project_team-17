package View;

import Controller.DiscountManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DiscountsMenu extends Menu {
    public DiscountsMenu(Menu parentMenu) {
        super("Discounts Menu", parentMenu);
    }

    @Override
    public void show() {
        System.out.println("1.offs");
        System.out.println("2.show Product");
        System.out.println("3.back");
    }

    @Override
    public void execute() {
        int input = Integer.parseInt(scanner.nextLine());
        if (input == 1) {
            showProducts();
            parentMenu.show();
            parentMenu.execute();
        } else if (input == 2) {
            showProducts();
            parentMenu.show();
            parentMenu.execute();
        }else if(input == 3){
            parentMenu.show();
            parentMenu.execute();
        }
    }

    public void showProducts() {
        String command;
        DiscountManager.showDiscountProducts();
        while (true) {
            command = scanner.nextLine();
            Pattern showProductByIdPattern = Pattern.compile("show product\\s(.+)");
            Matcher showProductByIdMatcher = showProductByIdPattern.matcher(command);
            if (command.matches("show product\\\\s(.+)")) {
                showProductByIdMatcher.find();
                //in k alan miad inja mibine id product ro baed mire to safeye hamon mahsol chejori handle konim k id
                //ro gereft bere to safeye hamoon product
            } else if (command.equals("help")) {
                System.out.println("commands that you can enter are:");
                System.out.println("show product [productID]");
                System.out.println("back");
            } else if (command.equals("back")) {
                break;
            } else System.out.println("Command is invalid");
        }

    }
}
