package View;

import java.util.HashMap;

public class MainMenu extends Menu {
    public MainMenu(){
        super("Main Menu" , null);
        HashMap<Integer, Menu> subMenus = new HashMap<Integer, Menu>();
        subMenus.put(1, new ManagerMenu(this));
        subMenus.put(2, new SellerMenu(this));
        subMenus.put(3, new CustomerMenu(this));
        subMenus.put(4, new ProductMenu(this));
        subMenus.put(5, new DiscountsMenu(this));
        this.setSubmenus(subMenus);

    }

}
