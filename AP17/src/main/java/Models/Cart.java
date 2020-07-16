package Models;

import Models.Accounts.Customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Cart {
    private Customer customer;
    private static ArrayList<Cart> allCarts = new ArrayList<>();

    public Cart(Customer customer) throws IOException {
        this.customer = customer;
        allCarts.add(this);
        //WriteIntoFile.writeCartsIntoFile();
    }

    private HashMap<Product, Integer> productsInCart = new HashMap<>();


    public void addProductToCart(Customer customer, Product product) {
        Cart cart = customer.getCart();
        if (cart.isThereProductInCart(product)) {
            int count = productsInCart.get(product);
            productsInCart.put(product, count + 1);
        } else {
            cart.productsInCart.put(product, 1);
        }
        customer.setCart(this);
    }

    public void increaseNumberOfProduct(Product product) {
        for (Product productInCart : productsInCart.keySet()) {
            if (productInCart.equals(product)) {
                int count = productsInCart.get(productInCart);
                productsInCart.replace(productInCart, count + 1);
            }
        }
    }

    public void decreaseNumberOfProduct(Product product) {
        for (Product cartProduct : productsInCart.keySet()) {
            if (cartProduct.equals(product)) {
                int count = productsInCart.get(cartProduct);
                productsInCart.replace(cartProduct, count - 1);
                if (productsInCart.get(cartProduct) == 0) {
                    productsInCart.remove(cartProduct);
                }
            }
        }
    }

    public void removeProductAfterPurchasingFromCart(Product product) {
        this.productsInCart.remove(product);
    }

    public ArrayList<Product> showProductsOfCart() {
        return new ArrayList<>(productsInCart.keySet());
    }

    public ArrayList<Product> getProductsInCart() {
        return new ArrayList<>(productsInCart.keySet());
    }

    public int numberOfProductInCart(Product product) {
        return productsInCart.get(product);
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
            double totalPrice = product.getPrice() * this.productsInCart.get(product);
            sum += totalPrice;
        }
        return sum;
    }

    public double totalPriceWithDiscount(DiscountCode discountCode) {
        return totalPriceOfProductInCart() - DiscountCode.calculateDiscountAmount(totalPriceOfProductInCart(), discountCode);
    }

    public static ArrayList<Cart> getAllCarts() {
        return allCarts;
    }
}