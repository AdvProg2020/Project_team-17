package Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DiscountCode {
    private String discountCode;
    private Date startDate;
    private Date endDate;
    private double discountPercent;
    private double maxDiscountAmount;
    private Map<Account, Integer> discountUseForEachPerson;
    private ArrayList<Account> allAccountsHaveDiscount;

    public DiscountCode(String discountCode, Date startDate, Date endDate, double discountPercent, double maxDiscountAmount) {
        this.discountCode = discountCode;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountPercent = discountPercent;
        this.maxDiscountAmount= maxDiscountAmount;
        discountUseForEachPerson = new HashMap<Account, Integer>();
        for (Account account : allAccountsHaveDiscount) {
            discountUseForEachPerson.put(account,0);
        }
    }
}
