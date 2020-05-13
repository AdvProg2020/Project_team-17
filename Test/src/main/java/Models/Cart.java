package Models;

import Models.Accounts.Customer;

import java.util.ArrayList;
import java.util.HashMap;

public class Cart {
    private HashMap<Product, Integer> productsInCart = new HashMap<>();

    public void addProductToCart(Customer customer, Product product) {
        Cart cart = customer.getCart();
        if (cart.isThereProductInCart(product)) {
            int count = productsInCart.get(product);
            productsInCart.put(product,count+1);

        } else {
            cart.productsInCart.put(product, 1);
        }
        customer.setCart(this);
    }

    public void increaseNumberOfProduct(Product product) {
        for (Product productInCart : productsInCart.keySet()) {
            if (productInCart.equals(product)) {
                int count = productsInCart.get(productInCart);
                productsInCart.put(productInCart , count+1);
            }
        }
    }

    public void decreaseNumberOfProduct(Product product) {
        for (Product productInCart : productsInCart.keySet()) {
            if (productInCart.equals(product)) {
                int count = productsInCart.get(productInCart);
                productsInCart.put(productInCart , count-1);
            }
        }

    }

    public ArrayList<Product> showProductsOfCart() {
        ArrayList<Product> allProductOfCart = new ArrayList<>();
        for (Product product : productsInCart.keySet()) {
            allProductOfCart.add(product);
        }
        return allProductOfCart;
    }

    public boolean isThereProductInCart(Product product) {
        for (Product productInCart : productsInCart.keySet()) {
            if (productInCart.equals(product)) {
                return true;
            }
        }
        return false;
    }

    public double totalPriceOfProductInCart() {
        double sum = 0;
        for (Product product : this.productsInCart.keySet()) {
            sum += product.getPrice();
        }
        return sum;
    }

    public HashMap<Product, Integer> getProductsInCart() {
        return productsInCart;
    }
}
