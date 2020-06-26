package Models.Request;

import Models.Accounts.Manager;
import Models.Accounts.Seller;
import Models.Category;
import Models.Enums.RequestTypeEnum;
import Models.Product;

import java.io.IOException;

public class RemoveProductRequest extends Request {
    private Product product;

    public RemoveProductRequest(Seller seller, Product product) throws IOException {
        super("Add product ---> " + (allRequests.size() + 1), seller);
        this.type = RequestTypeEnum.ADD_PRODUCT;
        this.product = product;
    }

    @Override
    public void accept() {
        Product.removeProduct(product);
        seller.removeProduct(seller, product);
    }

    @Override
    public String toString() {
        return "RemoveProductRequest{" +
                "product=" + product +
                ", id='" + id + '\'' +
                ", seller=" + seller +
                ", type=" + type +
                '}';
    }
}
