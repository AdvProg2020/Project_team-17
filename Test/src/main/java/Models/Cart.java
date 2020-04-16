package Models;

import java.util.HashMap;

public class Cart {
    private HashMap<Product,Integer> productsInCart = new HashMap<>();

    public void addProductToCart(Customer customer, Product product){
        productsInCart.put(product,1);
        customer.setCart(this);
    }
    public void increaseNumberOfProduct(Product product){

    }
    public void decreaseNumberOfProduct(Customer customer , Product product){

    }
    public double showTotalPrice(Cart cart){
        return 0;
    }

}
