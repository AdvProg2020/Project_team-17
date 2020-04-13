package Models;

public class Score {
    private Customer customer;
    private Product product;
    private double score;

    public Score(Customer customer, Product product, double score) {
        this.customer = customer;
        this.product = product;
        this.score = score;
    }
}
