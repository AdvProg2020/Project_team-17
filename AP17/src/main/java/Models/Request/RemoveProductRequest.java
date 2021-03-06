package Models.Request;

import Models.Accounts.Manager;
import Models.Accounts.Seller;
import Models.Category;
import Models.Enums.RequestStateEnum;
import Models.Enums.RequestTypeEnum;
import Models.Product;
import Server.ServerController.DataBaseForServer;

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
        DataBaseForServer.deleteProduct(product);
        this.setState(RequestStateEnum.ACCEPTED);
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
