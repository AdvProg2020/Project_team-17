package Models.Request;

import Models.Accounts.Manager;
import Models.Accounts.Seller;
import Models.Category;
import Models.Enums.ProductEnum;
import Models.Enums.RequestTypeEnum;
import Models.Product;

public class EditProductRequest extends Request {
    private Product product;
    private String field;
    private String newContentForThisField;

    public EditProductRequest(Seller seller, Manager manager, Product product, String field, String newContentForThisField) {
        super("Edit product ---> " + (allRequests.size() + 1), seller, manager);
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
        } else if (field.equals("companyName")) {
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