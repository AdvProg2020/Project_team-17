package Models;

import Models.Accounts.Customer;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class DiscountCode implements Serializable {
    private String discountCode;
    private LocalDate startDate;
    private LocalDate endDate;
    private double discountPercent;
    private double maxDiscountAmount;
    private int countDiscountCode;
    private Customer customer;
    private static ArrayList<DiscountCode> allDiscountCodes = new ArrayList<>();

    public DiscountCode(String discountCode, LocalDate startDate, LocalDate endDate, double discountPercent,
                        double maxDiscountAmount, int countDiscountCode, Customer customer) {
        this.discountCode = discountCode;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountPercent = discountPercent;
        this.maxDiscountAmount = maxDiscountAmount;
        this.countDiscountCode = countDiscountCode;
        this.customer = customer;
        allDiscountCodes.add(this);
    }

    public static ArrayList<DiscountCode> getAllDiscountCodes() {
        return allDiscountCodes;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public void setMaxDiscountAmount(double maxDiscountAmount) {
        this.maxDiscountAmount = maxDiscountAmount;
    }

    public void setCountDiscountCode(int countDiscountCode) {
        this.countDiscountCode = countDiscountCode;
    }


    public double getDiscountPercent() {
        return discountPercent;
    }

    public double getMaxDiscountAmount() {
        return maxDiscountAmount;
    }

    public int getCountDiscountCode() {
        return countDiscountCode;
    }

    public static void removeDiscountCode(DiscountCode discountCode) {
        allDiscountCodes.remove(discountCode);
    }

    public static boolean isThereDiscountCodeWithThisCode(String discountCode) {
        for (DiscountCode code : allDiscountCodes) {
            if (code.getDiscountCode().equals(discountCode)) {
                return true;
            }
        }
        return false;
    }

    public void setUsageOfDiscountCode(Customer customer) {
        int num = this.getCountDiscountCode() - 1;
        this.setCountDiscountCode(num);
        if (num == 0) {
            allDiscountCodes.remove(this);
            customer.removeDiscountCode(this);
        }

    }

    public static DiscountCode getDiscountCodeWithCode(String discountCode) {
        for (DiscountCode code : allDiscountCodes) {
            if (code.getDiscountCode().equals(discountCode)) {
                return code;
            }
        }
        return null;
    }

    public static double calculateDiscountAmount(double sum, DiscountCode discountCode) {
        if (discountCode == null) {
            return 0;
        }
        double sumWithDiscountPercent = (discountCode.getDiscountPercent() * sum) / 100;
        double sumWithMaximumAmount = discountCode.getMaxDiscountAmount();
        if (sumWithDiscountPercent > sumWithMaximumAmount) {
            return sumWithMaximumAmount;
        } else return sumWithDiscountPercent;
    }


    @Override
    public String toString() {
        return "DiscountCode{" +
                "discountCode='" + discountCode + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", discountPercent=" + discountPercent +
                ", maxDiscountAmount=" + maxDiscountAmount +
                ", countDiscountCode=" + countDiscountCode +
                ", customer=" + customer +
                '}';
    }
}