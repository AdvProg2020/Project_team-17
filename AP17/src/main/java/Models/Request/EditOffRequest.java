package Models.Request;

import Models.Accounts.Seller;
import Models.Discount;
import Models.Enums.DiscountEnum;
import Models.Enums.RequestStateEnum;
import Models.Enums.RequestTypeEnum;

import java.io.IOException;
import java.time.LocalDate;

public class EditOffRequest extends Request {
    private Discount discount;
    private String field;
    private String newContentForThisField;

    public EditOffRequest(Seller seller, Discount discount, String field, String newContentForThisField) throws IOException {
        super("Edit off ---> " + (allRequests.size() + 1), seller);
        this.type = RequestTypeEnum.EDIT_OFF;
        this.discount = discount;
        this.field = field;
        this.newContentForThisField = newContentForThisField;
        discount.setDiscountState(DiscountEnum.EDITING);
    }

    @Override
    public void accept() {
        if (field.equals("discount percent")) {
            discount.setDiscountPercent(Double.parseDouble(newContentForThisField));
        } else if (field.equals("enter start date as below(yyyy-mm-dd)")) {
            discount.setStartDate(LocalDate.parse(newContentForThisField));
        } else if (field.equals("enter end date as below(yyyy-mm-dd)")) {
            discount.setEndDate(LocalDate.parse(newContentForThisField));
        }
        discount.setDiscountState(DiscountEnum.ACCEPTED);
        this.setState(RequestStateEnum.ACCEPTED);
    }

    @Override
    public String toString() {
        return "EditOffRequest{" +
                "discount=" + discount +
                ", id='" + id + '\'' +
                ", seller=" + seller +
                ", type=" + type +
                '}';
    }
}
