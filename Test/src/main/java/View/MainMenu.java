package View;

import java.util.HashMap;

public class MainMenu extends Menu {
    public MainMenu() {
        super("Main Menu", null);
        HashMap<Integer, Menu> subMenus = new HashMap<Integer, Menu>();
        subMenus.put(1, new RegisterAndLoginMenu(this));
        subMenus.put(2, new ManagerMenu(this));
        subMenus.put(3, new SellerMenu(this));
        subMenus.put(4, new CustomerMenu(this));
        subMenus.put(5, new ProductsMenu(this));
        subMenus.put(6, new DiscountsMenu(this));
        this.setSubmenus(subMenus);
    }
    //bayad age raft to page dige joz product bere aval to login age login nakarde bood
}
