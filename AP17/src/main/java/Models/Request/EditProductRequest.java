package Models.Request;

import Models.Accounts.Seller;
import Models.Category;
import Models.Enums.ProductEnum;
import Models.Enums.RequestStateEnum;
import Models.Enums.RequestTypeEnum;
import Models.Product;

import java.io.IOException;

public class EditProductRequest extends Request {
    private Product product;
    private String field;
    private String newContentForThisField;

    public EditProductRequest(Seller seller,  Product product, String field, String newContentForThisField) throws IOException {
        super("Edit product ---> " + (allRequests.size() + 1), seller);
        this.type = RequestTypeEnum.EDIT_PRODUCT;
        this.product = product;
        this.field = field;
        this.newContentForThisField = newContentForThisField;
        product.setProductState(ProductEnum.EDITING);
    }

    @Override
    public void accept() {
        if (field.equals("name")) {
            product.setName(newContentForThisField);
        } else if (field.equals("company name")) {
            product.setCompanyName(newContentForThisField);
        } else if (field.equals("description")) {
            product.setExplanation(newContentForThisField);
        } else if (field.equals("seller")) {
            product.setSeller(Seller.getSellerByName(newContentForThisField));
        } else if (field.equals("price")) {
            product.setPrice(Double.parseDouble(newContentForThisField));
        } else if (field.equals("category")) {
            product.setCategory(Category.getCategoryByName(newContentForThisField));
        }
        product.setProductState(ProductEnum.ACCEPTED);
        this.setState(RequestStateEnum.ACCEPTED);
    }

    @Override
    public String toString() {
        return "EditProductRequest{" +
                "product=" + product +
                ", id='" + id + '\'' +
                ", seller=" + seller +
                ", type=" + type +
                '}';
    }
}