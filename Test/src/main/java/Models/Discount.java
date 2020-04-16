package Models;

import java.util.ArrayList;
import java.util.Date;

public class Discount {
    private String discountId;
    private  static ArrayList<Product> discountProducts = new ArrayList<>();

    public static ArrayList<Product> getDiscountProducts() {
        return discountProducts;
    }

    private Date startDate;
    private Date endDate;
    private double discountPercent;
    private DiscountEnum discountState;

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void addDiscountToProduct(Product product) {
        discountProducts.add(product);
    }
    public static void showDiscountProducts(){
        for (Product product: discountProducts
             ) {
            System.out.println("dadasha gheymat ghable takhfif :"+product.getPrice()+" " +
                    " va bade takhfif : "+product.getPriceAfterDiscount());
            // chiaro bayad chap konim?!
        }


    }

    public void deleteDiscountFromProduct(Product product) {

    }
}
