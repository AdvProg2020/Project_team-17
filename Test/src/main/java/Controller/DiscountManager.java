package Controller;

import Models.Accounts.Seller;
import Models.Discount;
import Models.Product;

import java.util.ArrayList;

public class DiscountManager {
    public static ArrayList<String> showDiscountProducts() {
        return Discount.showProductsHaveDiscount();
    }
    //filter va sort haram bayad bezanim?
}